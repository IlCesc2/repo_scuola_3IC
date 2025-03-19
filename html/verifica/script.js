
function submit(e){
    e.preventDefault()

    const nome = document.getElementById("nome")
    const cognome = document.getElementById("cognome")
    const email = document.getElementById("email")
    const check1 = document.getElementById("check1")
    const check2 = document.getElementById("check2")
    const check3 = document.getElementById("check3")
    const messaggio = document.getElementById("messaggio")

    if(!check1.checked && !check2.checked && !check3.checked){
        showErrorMessage("c1", "Selezionare almeno una delle opzioni")
        showErrorMessage("c2", "Selezionare almeno una delle opzioni")
        showErrorMessage("c3", "Selezionare almeno una delle opzioni")

        return
    }
    
    const output =`Grazie, ${nome.value} ${cognome.value} (email: ${email.value})! Il tuo messaggio di ${messaggio.value.length} caratteri è stato inviato con successo. Il nostro supporto tecnico ti contatterà al più presto`
    showSubmitMessage(output)

}

function showSubmitMessage(m){
    const form = document.getElementById("f")
    form.classList.remove("shown")
    form.classList.add("hidden")
    const message = document.getElementById("out")
    message.innerText=m
    
    message.classList.remove("hidden")
    message.classList.add("shown")

    
}

function showErrorMessage(id, message){
    const err = document.createElement("p")
    err.innerText= message
    err.classList.add("error")
    const el = document.getElementById(id)
    el.appendChild(err)
}

document.getElementById("f").addEventListener("submit", submit)

