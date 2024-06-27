function addRating() {
    const title = document.getElementById('title').value;
    const imdbRating = document.getElementById('imdbRating').value;
    const imdbVotes = document.getElementById('imdbVotes').value;

    fetch(`/movie/${title}/ratings`, {
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
    .then(data => alert('Rating added successfully!'))
    .catch(error => console.error('Error:', error));
}

function updateRating() {
    const title = document.getElementById('title').value;
    const imdbRating = document.getElementById('imdbRating').value;
    const imdbVotes = document.getElementById('imdbVotes').value;

    fetch(`/movie/${title}/ratings`, {
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
    .then(data => alert('Rating updated successfully!'))
    .catch(error => console.error('Error:', error));
}

function deleteRating() {
    const title = document.getElementById('title').value;

    fetch(`/movie/${title}/ratings`, {
        method: 'DELETE',
    })
    .then(response => response.json())
    .then(data => alert('Rating deleted successfully!'))
    .catch(error => console.error('Error:', error));
}
