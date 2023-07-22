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
                <td><a href="/todo/read?tno=${dto.tno}" class="text-decoration-none"/><c:out value="${dto.title}"/></td>
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


        self.location = `/todo/list?page=\${num}`
    },false)
</script>
</body>
</html>