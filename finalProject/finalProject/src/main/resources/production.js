const HOST = "http://localhost:8080"; // Replace with your actual backend host

document.getElementById('search-button').addEventListener('click', searchMovies);

function searchMovies() {
    var searchType = document.getElementById('search-type').value;
    var searchTerm = document.getElementById('search-input').value.trim();

    if (searchTerm === '') {
        alert('Please enter a ' + searchType + ' name.');
        return;
    }

    var endpoint = '';
    if (searchType === 'country') {
        endpoint = `${HOST}/movie/production/country/${encodeURIComponent(searchTerm)}`;
    } else if (searchType === 'language') {
        endpoint = `${HOST}/movie/production/language/${encodeURIComponent(searchTerm)}`;
    }

    fetch(endpoint)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Data received:', data); // Log the data received
            displayResults(data);
        })
        .catch(error => {
            console.error('Fetch error:', error); // Log fetch error for debugging
            alert('Error fetching movies. Please try again later.');
        });
}



function displayResults(movies) {
    var resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = ''; // Clear existing results

    var table = document.createElement('table');
    table.setAttribute('id', 'movies-table');
    resultsDiv.appendChild(table);

    var tableHead = document.createElement('thead');
    table.appendChild(tableHead);

    var headerRow = document.createElement('tr');
    tableHead.appendChild(headerRow);

    var headers = ['Movie Title', 'Director', 'Production Company', 'Languages', 'Country'];
    headers.forEach(headerText => {
        var headerCell = document.createElement('th');
        headerCell.textContent = headerText;
        headerRow.appendChild(headerCell);
    });

    var tableBody = document.createElement('tbody');
    table.appendChild(tableBody);

    if (movies.length === 0) {
        var noResultsRow = document.createElement('tr');
        var noResultsCell = document.createElement('td');
        noResultsCell.setAttribute('colspan', headers.length.toString());
        noResultsCell.textContent = 'No movies found.';
        noResultsRow.appendChild(noResultsCell);
        tableBody.appendChild(noResultsRow);
    } else {
        movies.forEach(movie => {
            var row = document.createElement('tr');

            var titleCell = document.createElement('td');
            titleCell.textContent = movie.title;
            row.appendChild(titleCell);

            var directorCell = document.createElement('td');
            directorCell.textContent = movie.director;
            row.appendChild(directorCell);

            var productionCell = document.createElement('td');
            productionCell.textContent = movie.production;
            row.appendChild(productionCell);

            var languageCell = document.createElement('td');
            languageCell.textContent = movie.language;
            row.appendChild(languageCell);

            var countryCell = document.createElement('td');
            countryCell.textContent = movie.country;
            row.appendChild(countryCell);

            tableBody.appendChild(row);
        });
    }
}