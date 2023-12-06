//checks if the autor already exist
var hasAuthor = false;
var currentFilter = "";
let authornames = [];
var firstID = "";

//methods of requesting: 
const REST_API = " http://localhost:3000/authors/"

getData(); 

//Deletes a row
document.getElementById('DeleteRow').addEventListener('submit', (e) => {

	console.log(document.getElementById('delete').value)
	const Author = document.getElementById('delete').value
	e.preventDefault();

	console.log("Deleting:",REST_API + Author)

	fetch(REST_API+ Author, {
        method: 'DELETE'
    })

	//and then call the get function to reassemble the table
	getData()
});

//Retrieves the data
function getData(){

	fetch(REST_API)
	.then(response => response.json())
	.then(data => {loadIntoTable(data)})
	.catch(error => console.error('Error:', error));
}

//Updates the form
document.getElementById('updateForm').addEventListener('submit', (e) => {

	e.preventDefault();
	formData = new FormData(updateForm);
	const targetRow = document.getElementById('author_id').value

	fetch(REST_API + targetRow,{
		method: "PUT",
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(Object.fromEntries(formData)),
	})
	.then(getData())

});

//Create a new resource 
document.getElementById('formElem').addEventListener('submit', (e) => {

	e.preventDefault();
	formData = new FormData(formElem);

	//
	fetch(REST_API,{
		method: "POST",
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(Object.fromEntries(formData)),
	})

	.then(getData())
	.catch(error => console.error('Error:', error));

});


//funtion to load the json data into the table
function loadIntoTable(data){

		console.log("Trying to build table with the following: ",data)

		firstID = data[0].id;
		for(var i = 0; i < data.length; i++) {

			var currentRow = data[i];
			var rowElement = document.createElement("tr");
			var properties = ['image', 'author', 'alt', 'tags', 'description'];

			for(var j = 0; j < properties.length; ++j) {
				var cell = document.createElement('td');

				if(j == 0) {

					var img = document.createElement('img');
					var text = document.createElement("p");
					autorName = currentRow[properties[1]];
					text.innerHTML = "Image of " + autorName
					img.src = currentRow[properties[j]];
					cell.appendChild(img);
					cell.appendChild(text);

				}else if(j == 1){

					cell.innerHTML = currentRow[properties[j]];
					cell.myParam = currentRow[properties[j]];

					if (authornames.includes(currentRow[properties[j]])) {
						hasAuthor = true;
					} else {
						authornames.push(currentRow[properties[j]]);
						hasAuthor = false;
					}
				}else{
					cell.innerHTML = currentRow[properties[j]];
				}

				rowElement.appendChild(cell);
				if(hasAuthor == true && j == 1) {
					rowElement.style.display = "none";
				}
			}
			document.getElementById('tablebody').appendChild(rowElement);
		}
}


//Modal script

var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn"); 
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

