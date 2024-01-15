console.log("comment js in!");
console.log(bnoVal);

document.getElementById('cmtAddBtn').addEventListener('click', ()=>{
    const cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText.value == ''){
        alert("댓글을 입력해주세요.");
        cmtText.focus();
        return false;
    }else{
        cmtData={
            bno:bnoVal,
            writer:document.getElementById('cmtWriter').innerText,
            content:cmtText.value
        };

        console.log(cmtData);

        postCommentToServer(cmtData).then(result =>{
            if(result === '1'){
                alert("댓글 등록 성공");
                cmtText.value = '';
            }
            //화면에 뿌리기
            spreadCommentList(cmtData.bno);

        })
    }
})

async function postCommentToServer(cmtData){
    try{

        const url = "/comment/post";
        const config = {
            method : 'post',
            headers : {
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();

        return result;


    }catch(error){
        console.log(error);
    }
}

async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json(); // commentList return
        return result;
    } catch (error) {
        console.log(error);
    }
}

function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result =>{
        console.log(result);
        const div = document.getElementById('accordionExample');
        if(result.cmtList.length > 0){
            //댓글을 다시 뿌릴때는 기존 값을 삭제X, 1page 일경우만 삭제
            if(page == 1){
                div.innerHTML = "";
            }
            for(let i=0; i<result.cmtList.length; i++){
                let add = `<div class="accordion-item"> `;
                add += `<h2 class="accordion-header">`;
                add += ` <button class="accordion-button" type="button"`
                add += `data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
                add += `cno.${result.cmtList[i].cno} 작성자 : ${result.cmtList[i].writer}</button></h2>`;
                add += `<div id="collapse${i}" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">`;
                add += `<div class="accordion-body" data-cno="${result.cmtList[i].cno}" data-writer="${result.cmtList[i].writer}">`;
                add += `<input type="text" class="form-control cmtText" value="${result.cmtList[i].content}"><br>`
                add += `<button type="button" data-cno="${result.cmtList[i].cno}" class="btn btn-sm btn-outline-success cmtModBtn" data-bs-toggle="modal" data-bs-target="#myModal">수정</button><span> | </span>`;
                add += `<button type="button" data-cno="${result.cmtList[i].cno}" class="btn btn-sm btn-outline-danger cmtDelBtn">삭제</button>`
                add += `</div></div></div>`;
                div.innerHTML += add;
            }
            // 댓글 더보기(more+) 버튼 코드
            let moreBtn =  document.getElementById('moreBtn');
            console.log(moreBtn);
            //more버튼 표시 조건
            if(result.pgvo.pageNo < result.endPage){
                moreBtn.style.visibility = 'visible'; //버튼 표시
                moreBtn.dataset.page = page + 1;
            }else{
                moreBtn.style.visibility = 'hidden';  //버튼 숨김
            }
        }else{
            div.innerHTML = `<div class="accordion-body">Comment List Empty</div>`;
        }
    })
}

document.addEventListener('click', (e)=>{
    console.log(e.target);
    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page); 
        spreadCommentList(bnoVal, page);
    }else if(e.target.classList.contains("cmtModBtn")){
        let div = e.target.closest('div');
        // let cmtText = div.querySelector('.accordion-body').nextSibilng; : 한 부모 안에서 같은(다음) 형제를 찾기
        let cmtText = div.querySelector(".cmtText").value;
        console.log(cmtText);
        //모달창에 기존 댓글 내용을 반영 
        document.getElementById('cmtTextMod').value = cmtText;

        //수정 => cno, writer, content
        document.getElementById('cmtModBtn').setAttribute("data-cno", div.dataset.cno);
        document.getElementById('cmtModBtn').setAttribute("data-writer", div.dataset.writer);

    }else if(e.target.id == 'cmtModBtn'){
        let cmtDataMod = {
            cno : e.target.dataset.cno,
            writer : e.target.dataset.writer,
            content : document.getElementById('cmtTextMod').value
        };
        console.log(cmtDataMod);
        //비동기 보내기
        updateCommentToServer(cmtDataMod).then(result=>{
            if(result == "1"){ // 수정성공
                //모달창 닫기
                document.querySelector('.btn-close').click();
                alert("댓글 수정 성공")
            }else{
                document.querySelector('.btn-close').click();
                alert("댓글 수정 실패");
            }
            //수정된 댓글 뿌리기 page=1
            spreadCommentList(bnoVal);
        })
    }else if(e.target.classList.contains('cmtDelBtn')){
        let div = e.target.closest('div');
        let cno = e.target.dataset.cno;
        deleteCommentToServer(cno).then(result=>{
            if(result === "1"){
                alert("댓글 삭제 성공");
                spreadCommentList(bnoVal);
            }
        })
    }

})

async function updateCommentToServer(cmtDataMod){
    try {
        const url = '/comment/update';
        const config = {
            method:"put",
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function deleteCommentToServer(cno){
    try {
        const url = '/comment/'+cno;
        const config = {
            method:"delete"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
