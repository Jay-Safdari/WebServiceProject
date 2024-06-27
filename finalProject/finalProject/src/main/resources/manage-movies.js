const HOST = "http://localhost:8080";

function addMovie() {
    var title = document.getElementById('title').value.trim();
    var director = document.getElementById('director').value.trim();
    var production = document.getElementById('production').value.trim();
    var language = document.getElementById('language').value.trim();
    var country = document.getElementById('country').value.trim();

    // Validate input fields
    if (title === '' || director === '' || production === '' || language === '' || country === '') {
        alert('Please fill in all fields.');
        return;
    }

    var movie = {
        title: title,
        director: director,
        production: production,
        language: language,
        country: country
    };

    fetch(`${HOST}/movie/production/`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(movie),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to add movie.');
        }
        alert('Movie added successfully!');
        clearFields();
    })
    .catch(error => {
        console.error('Error adding movie:', error);
        alert('Error adding movie. Please try again.');
    });
}

function updateMovie() {
    var id = prompt('Enter ID of the movie to update:');
    if (!id) return;

    var title = document.getElementById('title').value.trim();
    var director = document.getElementById('director').value.trim();
    var production = document.getElementById('production').value.trim();
    var language = document.getElementById('language').value.trim();
    var country = document.getElementById('country').value.trim();

    // Validate input fields
    if (title === '' || director === '' || production === '' || language === '' || country === '') {
        alert('Please fill in all fields.');
        return;
    }

    var movie = {
        title: title,
        director: director,
        production: production,
        language: language,
        country: country
    };

    fetch(`${HOST}/movie/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(movie),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to update movie.');
        }
        alert('Movie updated successfully!');
        clearFields();
    })
    .catch(error => {
        console.error('Error updating movie:', error);
        alert('Error updating movie. Please try again.');
    });
}

function deleteMovie() {
    var id = prompt('Enter ID of the movie to delete:');
    if (!id) return;

    fetch(`${HOST}/movie/${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to delete movie.');
        }
        alert('Movie deleted successfully!');
    })
    .catch(error => {
        console.error('Error deleting movie:', error);
        alert('Error deleting movie. Please try again.');
    });
}

function clearFields() {
    document.getElementById('title').value = '';
    document.getElementById('director').value = '';
    document.getElementById('production').value = '';
    document.getElementById('language').value = '';
    document.getElementById('country').value = '';
}
