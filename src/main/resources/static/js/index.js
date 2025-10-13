// You can add custom JavaScript here for features like dynamic searching,
// AJAX forms, or complex UI interactions.

document.addEventListener('DOMContentLoaded', function() {
    console.log('Library Management System client-side scripts loaded.');

    // Example: A simple script to highlight the active navigation link
    const currentPath = window.location.pathname;
    document.querySelectorAll('.navbar-nav .nav-link').forEach(link => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active');
            link.setAttribute('aria-current', 'page');
        }
    });
});