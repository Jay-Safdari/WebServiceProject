const HOST = "http://localhost:8080/movie";

async function searchMovie() {
  let title = document.getElementById("title").value;
  // let posterLink = document.getElementById("posterLink").value.trim();
  // title = "Matrix"; // Hardcoded for testing
  let url = `${HOST}/${title}/media`;

  try {
    const response = await fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const result = await response.json();
    showMovieMedia(result);
  } catch (error) {
    console.error("Error getting data:", error);
  }
}

function showMovieMedia(data) {
  console.log("Data received:", data);
  if (data != null) {
    var mediaDiv = document.getElementById("movie-media");
    mediaDiv.innerHTML = "";

    var table = document.createElement("table");
    table.setAttribute("id", "movies-table");
    mediaDiv.appendChild(table);

    var tableHead = document.createElement("thead");
    table.appendChild(tableHead);

    var headerRow = document.createElement("tr");
    tableHead.appendChild(headerRow);

    var headers = ["Movie Title", "Poster", "Poster Link"];
    headers.forEach((headerText) => {
      var headerCell = document.createElement("th");
      headerCell.textContent = headerText;
      headerRow.appendChild(headerCell);
    });

    var row = document.createElement("tr");
    table.appendChild(row);

    var titleCell = document.createElement("td");
    titleCell.textContent = data.title;
    row.appendChild(titleCell);

    var posterCell = document.createElement("td");
    if (data.posterLink != null) {
      posterCell.innerHTML = `<img src="${data.posterLink}" alt="Poster" width="200" height="300">`;
    }
    // posterLinkCell.textContent = data.posterLink;
    row.appendChild(posterCell);

    var posterLinkCell = document.createElement("td");
    posterLinkCell.textContent = data.posterLink;
    row.appendChild(posterLinkCell);

    let updateButton = document.getElementById("updateButton");
    updateButton.disabled = false;
  }
}

async function updatePosterlink() {
  let title = document.getElementById("title").value;
  let posterLink = document.getElementById("posterLink").value.trim();

  let url = `${HOST}/${title}/media`;

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ posterLink: posterLink }),
    });
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const result = await response.json();
    showMovieMedia(result);
  } catch (error) {
    console.error("Error posting data:", error);
  }
}
