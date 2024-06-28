const HOST = "http://localhost:8080";

document.getElementById('search-button').addEventListener('click', searchMovies);

document.getElementById('search-title-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission
    searchMoviesByTitle();
});

function searchMoviesByTitle() {
    var searchTerm = document.getElementById('title-search-input').value.trim();

    if (searchTerm === '') {
        alert('Please enter a search keyword.');
        return;
    }

    fetchMoviesByTitle(searchTerm);
}

function fetchMoviesByTitle(title) {
    const url = `${HOST}/movie/list/${encodeURIComponent(title)}`;
    
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(movies => {
            console.log('Movies found:', movies);
            displayMoviesList(movies);
        })
        .catch(error => {
            console.error('Fetch error:', error);
            alert('Error fetching movies by title. Please try again later.');
        });
}

function displayMoviesList(movies) {
    var moviesList = document.getElementById('movies-list');
    moviesList.innerHTML = ''; // Clear existing list

    if (movies.length === 0) {
        var noResultsItem = document.createElement('li');
        noResultsItem.textContent = 'No movies found.';
        moviesList.appendChild(noResultsItem);
    } else {
        movies.forEach(movie => {
            var listItem = document.createElement('li');
            listItem.textContent = movie.titleList;
            moviesList.appendChild(listItem);
        });
    }

    // Show the results section
    document.getElementById('search-results').style.display = 'block';
}

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
    } else if (searchType === 'title') {
        // Fetch basic info, details, ratings, production and media separately for 'title' search
        var basicInfoEndpoint = `${HOST}/movie/${encodeURIComponent(searchTerm)}/basicInfo`;
        var detailsEndpoint = `${HOST}/movie/${encodeURIComponent(searchTerm)}/details`;
        var ratingsEndpoint = `${HOST}/movie/${encodeURIComponent(searchTerm)}/ratings`;
        var productionEndpoint = `${HOST}/movie/${encodeURIComponent(searchTerm)}/production`;
        var mediaEndpoint = `${HOST}/movie/${encodeURIComponent(searchTerm)}/media`;

        // Make all fetch requests concurrently using Promise.all
        Promise.all([
            fetch(basicInfoEndpoint),
            fetch(detailsEndpoint),
            fetch(ratingsEndpoint),
            fetch(productionEndpoint),
            fetch(mediaEndpoint)
        ])
        .then(responses => {
            // Check if all responses are successful
            if (!responses.every(response => response.ok)) {
                throw new Error('HTTP error! One or more requests failed.');
            }
            // Parse responses as JSON
            return Promise.all(responses.map(response => response.json()));
        })
        .then(data => {
            // Log the data received
            console.log('Data received:', data);
            // Display basic info, details, ratings, and media
            displayMovieDetails(data[0], data[1], data[2], data[4]); // Assuming data[4] is media object
        })
        .catch(error => {
            // Log fetch error for debugging
            console.error('Fetch error:', error);
            // Alert user about the error
            alert('Error fetching movie details. Please try again later.');
        });

        return; // Exit function early to avoid further execution
    } else {
        alert('Invalid search type.'); // Handle invalid search type gracefully
        return;
    }
    
    // Handle other search types (country, language) here
    fetch(endpoint)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Data received:', data); // Log the data received
            displayResults(data); // Display list view for production search
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

function displayMovieDetails(basicInfo, details, ratings, media) {
    var resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = ''; // Clear existing results

    // Create a container for details
    var detailsContainer = document.createElement('div');
    resultsDiv.appendChild(detailsContainer);

    // Display movie poster (media)
    if (media && media.posterLink) {
        var posterImg = document.createElement('img');
        posterImg.src = media.posterLink;
        posterImg.alt = basicInfo.title; // Set alt attribute for accessibility
        detailsContainer.appendChild(posterImg);
    }

    // Display basic info
    var basicInfoList = document.createElement('ul');
    detailsContainer.appendChild(basicInfoList);

    var basicInfoItems = [
        { label: 'Movie Title', value: basicInfo.title },
        { label: 'Release date', value: basicInfo.released }
    ];

    basicInfoItems.forEach(item => {
        var li = document.createElement('li');
        li.textContent = `${item.label}: ${item.value}`;
        basicInfoList.appendChild(li);
    });

    // Display additional details
    var detailsList = document.createElement('ul');
    detailsContainer.appendChild(detailsList);

    var detailsItems = [
        { label: 'Genre', value: details.genre },
        { label: 'Plot', value: details.plot }
    ];

    detailsItems.forEach(item => {
        var li = document.createElement('li');
        li.textContent = `${item.label}: ${item.value}`;
        detailsList.appendChild(li);
    });

    // Display ratings
    var ratingList = document.createElement('ul');
    detailsContainer.appendChild(ratingList);

    var ratingItems = [
        { label: 'IMDB Rating', value: ratings.imdbRating },
        { label: 'Quantity of IMDB Votes', value: ratings.imdbVotes }
    ];

    ratingItems.forEach(item => {
        var li = document.createElement('li');
        li.textContent = `${item.label}: ${item.value}`;
        ratingList.appendChild(li);
    });
}

