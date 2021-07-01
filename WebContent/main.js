document.addEventListener('DOMContentLoaded', ()=>{
	console.log("Ispis..");
	let url = "data.xml";
	fetch (url)
	.then(response => response.text())
	.then(data => {
		let parser = new DOMParser();
		let xmlDOM = parser.parseFromString(data, 'application/xml');
		popuniTabelu(xmlDOM);
		
	})
})

function popuniTabelu(xml){
	let tbody = document.getElementById('result');
	let users = xml.querySelectorAll('user');
	
	for(let user of users){
		let tr = document.createElement('tr');
		
		//fullname
		let td = document.createElement('td');
		td.innerHTML = user.children[0].innerHTML + " "+ user.children[1].innerHTML;
		tr.appendChild(td);
		
		//address
		td = document.createElement('td');
		td.innerHTML = user.children[2].innerHTML;
		tr.appendChild(td);
		
		//city
		td = document.createElement('td');
		td.innerHTML = user.children[3].innerHTML;
		tr.appendChild(td);
		
		//country
		td = document.createElement('td');
		td.innerHTML = user.parentNode.getAttribute("name");
		tr.appendChild(td);
		
		//email
		td = document.createElement('td');
		td.innerHTML = user.children[4].innerHTML;
		tr.appendChild(td);
		
		//action
		td = document.createElement('td');
		let a = document.createElement('a');
		a.innerHTML = "DELETE";
		a.setAttribute("href", "shoutServlet?email="+user.children[4].innerHTML);
		td.appendChild(a);
		tr.appendChild(td);
		
		tbody.appendChild(tr);
	}
}

function checkEMail(){
	
	var pom = 0;
	let url = "data.xml";
	fetch (url)
	.then(response => response.text())
	.then(data => {
		let parser = new DOMParser();
		let xmlDOM = parser.parseFromString(data, 'application/xml');
		
		let emails = xmlDOM.querySelectorAll('email');
		let email = document.getElementById('email').value;
		
		for(let mail of emails){
			let resultCheck = email.localeCompare(mail.innerHTML);
			if (resultCheck == 0){
				pom = 1;
			}
		}
		
		if(pom==1){
			alert('The email has been already exist! Please enter a new e-mail!');
		}else{
			document.getElementById('form').submit();
		}
		
	})
}