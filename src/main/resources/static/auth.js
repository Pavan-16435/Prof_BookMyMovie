function register() {
    fetch("http://localhost:4040/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            email: email.value,
            password: password.value,
            role: role.value
        })
    })
    .then(res => res.json())
    .then(data => alert(data.message));
}

function login() {
    fetch("http://localhost:4040/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            email: email.value,
            password: password.value
        })
    })
    .then(res => res.json())
    .then(data => {

    
        if (!data.data) {
            alert(data.message);
            return;
        }

     
        localStorage.setItem("user", JSON.stringify(data.data));

       
        if (data.data.role === "ADMIN") {
            window.location.href = "/admin.html";
        } else {
            window.location.href = "/user.html";
        }
    })
    .catch(err => console.error(err));
}
	