// landing-page.component.ts
import { Component, OnInit, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss'],
  standalone: false,
  animations: [
    trigger('fadeInUp', [
      state('void', style({
        opacity: 0,
        transform: 'translateY(20px)'
      })),
      transition('void => *', [
        animate('0.6s ease-out')
      ])
    ]),
    trigger('fadeIn', [
      state('void', style({
        opacity: 0
      })),
      transition('void => *', [
        animate('0.8s ease-out')
      ])
    ]),
    trigger('slideInRight', [
      state('void', style({
        opacity: 0,
        transform: 'translateX(30px)'
      })),
      transition('void => *', [
        animate('0.6s ease-out')
      ])
    ])
  ]
})
export class LandingPageComponent implements OnInit, AfterViewInit {
  @ViewChild('canvas') canvasRef!: ElementRef<HTMLCanvasElement>;

  teamMembers = [
    {
      name: 'Vansh Purohit',
      title: 'Lead Developer',
      image: 'assets/vansh.jpg',
      description: 'Expert in Full Stack Development with over 3 years experience.',
      social: {
        twitter: '#',
        linkedin: '#',
        github: '#'
      }
    },
    {
      name: 'Aminu Muazu',
      title: 'Frontend Developer',
      image: 'assets/aminu.jpg',
      description: 'Passionate about creating intuitive user experiences for educators.',
      social: {
        twitter: '#',
        linkedin: '#',
        github: '#'
      }
    },
    {
      name: 'Manav Hingorani',
      title: 'Data Analytics Specialist',
      image: 'assets/manav.jpg',
      description: 'Former professor with a deep understanding of course management needs.',
      social: {
        twitter: '#',
        linkedin: '#',
        github: '#'
      }
    },
    {
      name: 'Fawzi Tahboub',
      title: 'UI / UX Specialist',
      image: 'assets/fawzi.jpg',
      description: 'Driving innovation in educational software products.',
      social: {
        twitter: '#',
        linkedin: '#',
        github: '#'
      }
    }
  ];

  features = [
    {
      icon: "fa-calendar-check",
      title: 'Assessment Management',
      description: 'Create and organize various assessment types with percentage weights and scheduling.'
    },
    {
      icon: 'fa-brain',
      title: 'CLO Mapping',
      description: 'Map Course Learning Outcomes to specific assessments to track achievement of learning goals.'
    },
    {
      icon: 'fa-chart-bar',
      title: 'Performance Analytics',
      description: 'Visualize student performance with powerful analytics and reporting tools.'
    },
    {
      icon: 'fa-file-alt',
      title: 'Syllabus Generation',
      description: 'Automatically generate and export professional course syllabi in PDF format.'
    },
    {
      icon: 'fa-tasks',
      title: 'Drag & Drop Calendar',
      description: 'Intuitively schedule assessments with an interactive calendar interface.'
    },
    {
      icon: 'fa-book',
      title: 'Resource Management',
      description: 'Track required and suggested reading materials for your courses.'
    }
  ];

  testimonials = [
    {
      quote: "CourseGenie has revolutionized how I manage my courses. The CLO mapping feature has been invaluable for accreditation.",
      author: "Dr. Emily Chen",
      position: "Professor of Computer Science",
      university: "Tech University"
    },
    // {
    //   quote: "The assessment scheduling and grade analytics have saved me countless hours of work each semester.",
    //   author: "Prof. James Wilson",
    //   position: "Associate Professor of Business",
    //   university: "State College"
    // },
    // {
    //   quote: "As a department chair, CourseGenie has made curriculum management across multiple courses effortless.",
    //   author: "Dr. Robert Garcia",
    //   position: "Department Chair",
    //   university: "Liberal Arts College"
    // }
  ];

  constructor(private router: Router) {
  }

  ngOnInit() {
    // Initialize any data or services
  }

  ngAfterViewInit() {
    this.initParticles();
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }

  // Fixed initParticles method
  initParticles() {
    const canvas = this.canvasRef.nativeElement;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    const particles: any[] = [];
    const particleCount = 100;

    for (let i = 0; i < particleCount; i++) {
      particles.push({
        x: Math.random() * canvas.width,
        y: Math.random() * canvas.height,
        radius: Math.random() * 3 + 1,
        color: `rgba(255, 255, 255, ${Math.random() * 0.5 + 0.1})`,
        speedX: Math.random() * 1 - 0.5,
        speedY: Math.random() * 1 - 0.5
      });
    }

    function animate() {
      requestAnimationFrame(animate);
      if (!ctx) return;

      ctx.clearRect(0, 0, canvas.width, canvas.height);

      // Draw particles
      particles.forEach(particle => {
        ctx.beginPath();
        ctx.arc(particle.x, particle.y, particle.radius, 0, Math.PI * 2);
        ctx.fillStyle = particle.color;
        ctx.fill();

        // Update particle positions
        particle.x += particle.speedX;
        particle.y += particle.speedY;

        // Bounce particles off edges
        if (particle.x < 0 || particle.x > canvas.width) particle.speedX *= -1;
        if (particle.y < 0 || particle.y > canvas.height) particle.speedY *= -1;
      });

      // Draw connections between particles
      particles.forEach((a, i) => {
        particles.slice(i + 1).forEach(b => {
          const dx = a.x - b.x;
          const dy = a.y - b.y;
          const distance = Math.sqrt(dx * dx + dy * dy);

          if (distance < 100) {
            ctx.beginPath();
            ctx.strokeStyle = `rgba(255, 255, 255, ${0.2 - distance / 500})`;
            ctx.lineWidth = 0.5;
            ctx.moveTo(a.x, a.y);
            ctx.lineTo(b.x, b.y);
            ctx.stroke();
          }
        });
      });
    }

    animate();

    // Handle window resize
    window.addEventListener('resize', () => {
      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;
    });
  }
}
