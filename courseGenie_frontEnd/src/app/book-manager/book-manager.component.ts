import { Component, OnInit } from '@angular/core';
import { BookService, Book } from '../services/book.service';
import { SharedDataService } from '../services/shared-data.sevice';
import { Course } from '../home/course.model';

@Component({
  selector: 'app-book-mapping',
  templateUrl: './book-manager.component.html',
  styleUrls: ['./book-manager.component.scss'],
  standalone: false
})
export class BookManagerComponent implements OnInit {

  // All books loaded from backend.
  books: Book[] = [];
  // Data displayed in the table (filtered).
  tableData: Book[] = [];

  // Local mapping state for each book id.
  mapping: { [bookId: number]: { mapped: boolean, requiredReading: boolean, suggestedReading: boolean } } = {};

  course: Course | null = null;
  currentSectionId: number | null = null;

  error: string = '';
  showSuccessMessage: boolean = false;
  loading: boolean = false;

  // Search term for filtering.
  searchTerm: string = '';

  // Import Book form state.
  showImportForm: boolean = false;
  isbnInput: string = '';

  constructor(
    private bookService: BookService,
    private sharedDataService: SharedDataService
  ) {}

  ngOnInit(): void {
    // Get current course from shared service.
    this.course = this.sharedDataService.getSharedVariable();
    if (!this.course || !this.course.sections || this.course.sections.length === 0) {
      this.error = 'No course/section selected or course data is missing.';
    } else {
      // Use the first section.
      this.currentSectionId = this.course.sections[0].sectionId;
    }
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getAllBooks().subscribe({
      next: (books: Book[]) => {
        this.books = books;
        // Initialize mapping state for each book.
        if (this.currentSectionId != null) {
          books.forEach(book => {
            if (typeof this.currentSectionId === "number") {
              this.mapping[book.id] = {
                mapped: book.sectionIds ? book.sectionIds.includes(this.currentSectionId) : false,
                requiredReading: book.requiredReading,
                suggestedReading: book.suggestedReading
              };
            }
          });
        }
        // Initially show all books.
        this.tableData = [...this.books];
      },
      error: (err) => {
        console.error(err);
        this.error = 'Error loading books.';
      }
    });
  }

  // Filter the table based on ISBN or title.
  onSearchTermChange(): void {
    if (!this.searchTerm.trim()) {
      this.tableData = [...this.books];
    } else {
      const term = this.searchTerm.toLowerCase();
      this.tableData = this.books.filter(book =>
        book.isbn.toLowerCase().includes(term) ||
        book.title.toLowerCase().includes(term)
      );
    }
  }

  // Called when a checkbox is toggled.
  onCheckboxChange(event: any, book: Book, type: 'mapped' | 'requiredReading' | 'suggestedReading'): void {
    // If the mapping for this book is not initialized, initialize it.
    if (!this.mapping[book.id]) {
      this.mapping[book.id] = { mapped: false, requiredReading: false, suggestedReading: false };
    }
    this.mapping[book.id][type] = event.target.checked;
  }
  // Add these properties and methods to your component class:

// Header buttons configuration
  headerButtons = [
    // {
    //   label: 'Save',
    //   icon: 'save',
    //   action: 'save-configuration',
    //   class: 'primary-btn'
    // }
  ];

// Handle header button clicks
  handleHeaderAction(action: string): void {
    if (action === 'save-configuration') {
      this.saveConfiguration();
    }
  }
  // Save the mapping configuration.
  saveConfiguration(): void {
    if (this.currentSectionId == null) {
      this.error = 'No current section available.';
      return;
    }
    this.books.forEach(book => {
      const local = this.mapping[book.id];
      if (!local) return;

      console.log('Updating book mapping:', {
        bookId: book.id,
        sectionId: this.currentSectionId,
        mapping: local
      });

      if (this.currentSectionId != null) {
        this.bookService.updateMapping(
          book.id,
          this.currentSectionId,
          local.mapped,
          local.requiredReading,
          local.suggestedReading
        ).subscribe({
          next: () => {
            this.loadBooks();
            this.showSuccessMessage = true;
          },
          error: (err) => {
            console.error(err);
            this.error = `Error updating mapping for book ${book.title}`;
          }
        });
      }
    });
  }

  closeSuccessMessage(): void {
    this.showSuccessMessage = false;
  }

  // Toggle the import book form.
  toggleImportForm(event?: Event): void {
    if (event) {
      event.stopPropagation();
    }
    console.log('toggleImportForm called; current value:', this.showImportForm);
    this.showImportForm = !this.showImportForm;
    console.log('new value:', this.showImportForm);
    this.error = '';
  }

  // Import a book by ISBN.
  importBook(): void {
    if (!this.isbnInput.trim() || this.currentSectionId == null) {
      this.error = 'Please enter an ISBN and ensure a section is selected.';
      return;
    }
    this.loading = true;
    this.bookService.importBook(this.isbnInput).subscribe({
      next: (book: Book) => {
        this.loading = false;
        this.error = '';
        this.isbnInput = '';
        this.showImportForm = false;
        this.loadBooks();
      },
      error: (err) => {
        this.loading = false;
        console.error(err);
        this.error = 'Error importing book. Please try again.';
      }
    });
  }
}
