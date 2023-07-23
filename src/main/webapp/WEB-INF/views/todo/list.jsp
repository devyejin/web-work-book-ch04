<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>

<!-- header end -->
<!-- 기존의 <h1>Header</h1>끝 -->
<div class="row content">
    <div class="col">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Search </h5>
                <form action="/todo/list" method="get">
                    <input type="hidden" name="size" value="${pageRequestDTO.size}">
                    <div class="mb-3">
                        <input type="checkbox" name="finished" ${pageRequestDTO.finished?"checked":""} >완료여부
                    </div>
                    <div class="mb-3">
                        <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t")?"checked":""}>제목
                        <input type="checkbox" name="types" value="w"  ${pageRequestDTO.checkType("w")?"checked":""}>작성자
                        <input type="text"  name="keyword" class="form-control" value ='<c:out value="${pageRequestDTO.keyword}"/>' >
                    </div>
                    <div class="input-group mb-3 dueDateDiv">
                        <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                        <input type="date" name="to" class="form-control"  value="${pageRequestDTO.to}">
                    </div>
                    <div class="input-group mb-3">
                        <div class="float-end">
                            <button class="btn btn-primary" type="submit">Search</button>
                            <button class="btn btn-info clearBtn" type="reset">Clear</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>


<div class="card-body">
    <h5 class="card-title">Special title treatment</h5>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Tno</th>
            <th scope="col">Title</th>
            <th scope="col">Writer</th>
            <th scope="col">DueDate</th>
            <th scope="col">Finished</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${responseDTO.dtoList}" var="dto">
            <tr>
                <th scope="row"><c:out value="${dto.tno}"/></th> <!--c:out 변수 값 출력 -->
                <td><a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}" class="text-decoration-none"/><c:out value="${dto.title}"/></td>
                <td><c:out value="${dto.writer}"/></td>
                <td><c:out value="${dto.dueDate}"/><td>
                <td><c:out value="${dto.finished}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="float-end">
        <ul class="pagination flex-wrap">
            <c:if test="${responseDTO.prev}">
                <li class="page-item">
                    <a href="#" class="page-link" data-num="${responseDTO.start-1}">이전</a>
                </li>
            </c:if>

            <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var = "num" >
                <li class="page-item" ${responseDTO.page == num ? "active" : ""}>
                    <a href="#" data-num=${num} class="page-link" >${num}</a> <!-- data-num=${num} data-num속성에 페이지값 저장 -->
                </li>
            </c:forEach>

            <c:if test="${responseDTO.next}">
                <li class="page-item">
                    <a href="#" data-num="${responseDTO.end + 1}" class="page-link">다음</a>
                </li>
            </c:if>

        </ul>
    </div>

</div>
<script>
    //clear버튼 이벤트
    document.querySelector(".clearBtn").addEventListener("click", e => {
        e.preventDefault();
        e.stopPropagation();

        self.location = '/todo/list';
    },false)


//    <ul> 전체에 이벤트를 걸기
    document.querySelector(".pagination").addEventListener("click",function (e){
        console.log(("클릭 이벤트 걸리는중"))
        e.preventDefault();
        e.stopPropagation();

        const target = e.target;


        if(target.tagName !== 'A') {

            return; //이벤트 안검
        }

        const num = target.getAttribute("data-num");

        //정보들은 form에 있으니까
        const formObj = document.querySelector("form");

        //page정보는 없으니까 page정보 추가
        formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`;
        formObj.submit();

    },false)
</script>
</body>
</html>