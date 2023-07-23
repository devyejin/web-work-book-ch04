<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<div class="card-body">

    <form action="/todo/modify" method="post">


        <div class="input-group mb-3">
            <span class="input-group-text">TNO</span>
            <input type="text" name="tno" class="form-control"
                   value=
                   <c:out value="${dto.tno}"></c:out> readonly> <!-- 출력 -->
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">Title</span>
            <input type="text" name="title" class="form-control"
                   value='<c:out value="${dto.title}"></c:out>'>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text">DueDate</span>
            <input type="date" name="dueDate" class="form-control"
                   value=
                   <c:out value="${dto.dueDate}"></c:out>>

        </div>

        <div class="input-group mb-3">
            <span class="input-group-text">Writer</span>
            <input type="text" name="writer" class="form-control"
                   value=
                   <c:out value="${dto.writer}"></c:out> readonly>
        </div>

        <div class="form-check">
            <label class="form-check-label">
                Finished &nbsp;
            </label>
            <input class="form-check-input" type="checkbox" name="finished"
            ${dto.finished ? "checked" : ""} > <!-- true이면 checked속성 추가 -->
        </div>

        <div class="my-4">
            <div class="float-end">
                <button type="button" class="btn btn-danger">Remove</button>
                <button type="button" class="btn btn-primary">Modify</button>
                <button type="button" class="btn btn-secondary">List</button>
            </div>
        </div>
    </form>
</div>
<script>
<%--    Valid 후 에러있는 경우 --%>
    const serverValidResult = {};

    <c:forEach items="${errors}" var="error">  <!-- items에는 colelction 변수명-->
        serverValidResult['${error.getField()}'] = '${error.defaultMessage}';
    </c:forEach>

console.log(serverValidResult);


<%--    버튼에 이벤트 거는 JS 코드 --%>
    const formObj = document.querySelector("form")

 //   <!-- addEventListener false 옵션 : 기본값이 false, true로하면 이벤트가 단 한번만 사용됨-->
    document.querySelector(".btn-danger").addEventListener("click",(e) => {
        e.preventDefault();
        e.stopPropagation();
        formObj.action = `/todo/remove?${pageRequestDTO.link}`;
        formObj.method = "post";

        formObj.submit();

    })

document.querySelector(".btn-primary").addEventListener("click", function (e) {

        //console.log(self.location); //<-- 이건 getter로 동작 (참고용)
        //self.location = "/todo/modify?tno=" +${dto.tno} // <-- 이건 setter로동작

        e.preventDefault();
        e.stopPropagation();

        formObj.action = "/todo/modify"; //ㄷㅂㄷㅂ
        formObj.method = "post";

        formObj.submit();

    }, false);

    document.querySelector(".btn-secondary").addEventListener("click", function (e) {
        e.preventDefault();
        e.stopPropagation();

        self.location = `/todo/list?${pageRequestDTO.link}`;
    }, false)

</script>
</body>
</html>
