const user = JSON.parse(localStorage.getItem("user"));
if (!user || user.role !== "ADMIN") {
    window.location.href = "/login.html";
}

if (!user) {
    window.location.href = "/login.html";
}
if (user.role !== "ADMIN") {
    window.location.href = "/login.html";
}
function loadMovies() {
    fetch("http://localhost:4040/admin/movies")
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("movieTable");
            table.innerHTML = "";

            data.data.forEach(movie => {
                table.innerHTML += `
                    <tr>
                        <td>${movie.id}</td>
                        <td>${movie.name}</td>
                        <td>${movie.tickets}</td>
                        <td>
                            <button onclick="deleteMovie(${movie.id})">Delete</button>
                        </td>
                    </tr>
                `;
            });
        });
}

// ADD MOVIE
function addMovie() {
    const name = document.getElementById("name").value;
    const tickets = document.getElementById("tickets").value;

    if (!name || !tickets) {
        alert("Fill all fields");
        return;
    }
	fetch("http://localhost:4040/admin/movies", {
	    method: "POST",
	    headers: {
	        "Content-Type": "application/json",
	        "Role": user.role
	    },
	    body: JSON.stringify({ name, tickets })
	});
	
    fetch("http://localhost:4040/admin/movies", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, tickets })
    })
    .then(res => res.json())
    .then(() => {
        document.getElementById("name").value = "";
        document.getElementById("tickets").value = "";
        loadMovies(); // refresh table
    });
}

// DELETE MOVIE
function deleteMovie(id) {
	fetch(`http://localhost:4040/admin/movies/${id}`, {
	    method: "DELETE",
	    headers: {
	        "Role": user.role
	    }
	});
    fetch(`http://localhost:4040/admin/movies/${id}`, {
        method: "DELETE"
    })
    .then(res => res.json())
    .then(() => loadMovies());
}

function updateMovie() {
    const id = document.getElementById("updateId").value;
    const name = document.getElementById("updateName").value;
    const tickets = document.getElementById("updateTickets").value;

    if (!id || !name || !tickets) {
        alert("Fill all fields");
        return;
    }
	fetch(`http://localhost:4040/admin/movies/${id}`, {
	    method: "PUT",
	    headers: {
	        "Content-Type": "application/json",
	        "Role": user.role
	    },
	    body: JSON.stringify({ name, tickets })
	});

    fetch(`http://localhost:4040/admin/movies/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, tickets })
    })
    .then(res => res.json())
    .then(data => {
        alert(data.message);
        loadMovies(); // refresh table
    })
    .catch(err => console.error(err));
}

function logout() {
    localStorage.clear();
    window.location.href = "/Login.html";
}
