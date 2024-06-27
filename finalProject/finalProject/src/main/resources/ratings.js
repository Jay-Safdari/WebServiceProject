const HOST = "http://localhost:8080"; 

function addRating() {
    const title = document.getElementById('title').value.trim();
    const imdbRating = document.getElementById('imdbRating').value.trim();
    const imdbVotes = document.getElementById('imdbVotes').value.trim();

    if (!title || !imdbRating || !imdbVotes) {
        alert('Please fill out all fields.');
        return;
    }

    fetch(`${HOST}/movie/${title}/ratings`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: title,
            imdbRating: imdbRating,
            imdbVotes: imdbVotes
        })
    })
    .then(response => response.json())
    .then(data => {
        alert('Rating added successfully!');
    })
    .catch(error => console.error('Error:', error));
}

function updateRating() {
    const title = document.getElementById('title').value.trim();
    const imdbRating = document.getElementById('imdbRating').value.trim();
    const imdbVotes = document.getElementById('imdbVotes').value.trim();

    if (!title) {
        alert('Please enter a movie title.');
        return;
    }

    fetch(`${HOST}/movie/${title}/ratings`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            imdbRating: imdbRating,
            imdbVotes: imdbVotes
        })
    })
    .then(response => response.json())
    .then(data => {
        alert('Rating updated successfully!');
    })
    .catch(error => console.error('Error:', error));
}

function deleteRating() {
    const title = document.getElementById('title').value.trim();

    if (!title) {
        alert('Please enter a movie title.');
        return;
    }

    fetch(`${HOST}/movie/${title}/ratings`, {
        method: 'DELETE',
    })
    .then(response => response.json())
    .then(data => {
        alert('Rating deleted successfully!');
    })
    .catch(error => console.error('Error:', error));
}
