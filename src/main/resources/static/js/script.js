
    // Get the current page URL
    const currentPage = window.location.href;

    // Get all tabs
    const tabs = document.querySelectorAll('.tab');

    // Loop through tabs to find the active one based on the current page
    tabs.forEach(tab => {
        console.log(Element)
    const link = tab.querySelector('a');
    if (link.href === currentPage) {
    tab.classList.add('active');
}
});

