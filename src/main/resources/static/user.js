if (user.role !== "USER") {
    window.location.href = "/login.html";
}
function loadMovies() {
    fetch("http://localhost:4040/user/movies")
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("movieTable");
            table.innerHTML = "";

            data.data.forEach(movie => {
                table.innerHTML += `
                    <tr>
                        <td>${movie.name}</td>
                        <td>${movie.tickets}</td>
                        <td>
                            <button onclick="prefill('${movie.name}')">Book</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function prefill(name) {
    document.getElementById("movieName").value = name;
}

function bookTicket() {
    const movieName = document.getElementById("movieName").value;
    const quantity = document.getElementById("quantity").value;

    if (!movieName || !quantity) {
        alert("Fill all fields");
        return;
    }

    fetch(`http://localhost:4040/user/book/${movieName}?quantity=${quantity}`, {
        method: "PUT"
    })
    .then(res => res.json())
    .then(data => {
        alert(data.message);
        loadMovies(); // refresh tickets
    })
    .catch(err => console.error(err));
}

function logout() {
    localStorage.clear();
    window.location.href = "/Login.html";
}
