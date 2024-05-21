const progressBar = () =>{
    console.log("clicked");

    let progressBar = document.createElement('div');
    progressBar.className = 'overlay';
    progressBar.innerText = ''; // You can customize this text

    progressBar.classList.add('progress-bar');
    var width = 1;
    var id = setInterval(frame, 10);
    function frame() {
        if (width >= 100) {
            clearInterval(id);
        } else {
            width++;
            progressBar.style.width = width + '%';
        }
    }
    // Position the overlay over the header
    document.body.appendChild(progressBar);
}

const closeModalDialog = ()=> {
    let modal = document.getElementById("errorModal");
    console.log(modal);
    modal.style.display = "none";


    fetch("/clearErrorMessage")
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
        })
        .then(data => {
            console.log("Error message cleared");
        })
        .catch(error => {
            console.error("There was a problem with the fetch operation:", error);
        });
}
