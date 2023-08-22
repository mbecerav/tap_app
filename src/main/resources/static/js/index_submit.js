/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


function sendAddAppForm(){
    const formData = new FormData(document.getElementById('applicantForm'));
    const form = document.getElementById('applicantForm');

    fetch('/addApp', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            console.log('Datos enviados correctamente');
            alert('Applicant registered!');
            form.reset();
        } else {
            console.error('Error al enviar los datos');
        }
    })
    .catch(error => {
        console.error('Error de red:', error);
    });
    
}


async function sendUpdateForm(){
    const form = document.getElementById('updateEvalForm');

    await fetch('/updateEvaluation', {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            console.log('Datos enviados correctamente');
            return response.json();
        } else {
            console.error('Error al enviar los datos');
        }
    })
    .then(data => {
                console.log(data);
                document.getElementById('pass').innerHTML = data[0];
                document.getElementById('fails').innerHTML = data[1];
    })
    .catch(error => {
        console.error('Error de red:', error);
    });
    
    drawChart();
}

async function deleteCategory(clicked_id){
    //alert(clicked_id);
    await fetch(`/deleteCategory/${clicked_id}`, {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            console.log('Datos enviados correctamente');
            setTimeout(function() {
                window.location.reload();
            }, 1000); 
        } else {
            console.error('Error al enviar los datos');
        }
    })
    .catch(error => {
        console.error('Error de red:', error);
    });
    
}

async function addCategory(){
    //alert(clicked_id);
    const formData = new FormData(document.getElementById('newCategoryForm'));
    const form = document.getElementById('newCategoryForm');

    await fetch('/addCategory', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            console.log('Datos enviados correctamente');
            setTimeout(function() {
                window.location.reload();
            }, 1000); 
        } else {
            console.error('Error al enviar los datos');
        }
    })
    .catch(error => {
        console.error('Error de red:', error);
    });
    
}

async function deleteSubject(clicked_id){
    //alert(clicked_id);
    await fetch(`/deleteSubject/${clicked_id}`, {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            console.log('Datos enviados correctamente');
            setTimeout(function() {
                window.location.reload();
            }, 1000); 
        } else {
            console.error('Error al enviar los datos');
        }
    })
    .catch(error => {
        console.error('Error de red:', error);
    });
    
}

async function updateSubjectCategory(changedItem){
    var x = document.getElementById(changedItem).value;
    var subCat = changedItem + x;
    await fetch(`/updateSubjectCategory/${subCat}`, {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            console.log('Datos enviados correctamente');
            setTimeout(function() {
                //window.location.reload();
            }, 1000); 
        } else {
            console.error('Error al enviar los datos');
        }
    })
    .catch(error => {
        console.error('Error de red:', error);
    });
    
}