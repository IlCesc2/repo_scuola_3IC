let results = []

async function onInputValueChange(){
    const value = document.querySelector("input").value
    console.log(value)
    await fetch(`https://openlibrary.org/search.json?q=${value}`).then(async (res)=>{
        const data = await res.json()
        results= data.docs
        console.log(results)
        refreshResults()
    })
   
}

function refreshResults(){

    const res = document.getElementById("results")
    res.textContent=""

    results.forEach((r)=>{
        console.log(r)
        const newBook = document.createElement("div")
        const title = document.createElement("h2")
        const img = document.createElement("img")
        title.textContent=r.title
        img.src=`https://covers.openlibrary.org/b/id/${r.cover_i}.jpg`
        newBook.appendChild(title)
        newBook.appendChild(img)
        res.appendChild(newBook)
    })
}
document.querySelector("input").addEventListener("input",(e)=> onInputValueChange())
