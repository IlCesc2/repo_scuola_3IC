let results = []

async function onInputValueChange(){
    results=[]
    const value = document.querySelector("input").value
    document.getElementById("loading").style.display= "inline"
    const url =`https://openlibrary.org/search.json?title=${value}` // "https://openlibrary.org/search.json?q=bible"    
    await fetch(url).then(async (res)=>{
        const data = await res.json()
        results= data.docs
        console.log(results)
        refreshResults()
        document.getElementById("loading").style.display="none"
    })
   
}


function refreshResults(){

    const res = document.getElementById("results")
    res.textContent=""

    results.forEach((r)=>{
        console.log(r)
        const newBook = document.createElement("div")
        newBook.classList.add("book")
        const title = document.createElement("h2")
        const img = document.createElement("img")
        title.textContent=r.title
        img.src=`https://covers.openlibrary.org/b/id/${r.cover_i}.jpg`
        img.classList.add("cover")
        newBook.appendChild(title)
        newBook.appendChild(img)
        res.appendChild(newBook)
    })
}
document.querySelector("input").addEventListener("input",(e)=> onInputValueChange())

//onInputValueChange()