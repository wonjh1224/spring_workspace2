console.log("boardModify.js in~!");

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains("file-x")){
        console.log(e.target.dataset.uuid);
        let uuid = e.target.dataset.uuid;
        deleteFile(uuid).then(result=>{
            console.log(result);
            if(result === "1"){
                alert("파일 삭제 성공");
                e.target.closest('li').remove();
            }
        })
    }
})

async function deleteFile(uuid){
    
try {
    
    const url = '/board/'+uuid;
    const config = {
        method : "delete"
    }
    const resp = await fetch(url, config);
    const result = await resp.text();

    return result;


} catch (error) {
    console.log(error);
}

}