<%--
  Created by IntelliJ IDEA.
  User: jiyoon
  Date: 2/11/25
  Time: 5:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Navbar</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Link</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Dropdown
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Action</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="#">Something else here</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                            </li>
                        </ul>
                        <form class="d-flex" role="search">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>
        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Search</h5>
                        <form id="searchForm" action="/todo/list" method="get">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="mb-3">
                                <input type="checkbox" name="finished" ${pageRequestDTO.finished ? "checked" : ""}/>완료 여부
                            </div>
                            <div class="mb-3">
                                <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t") ? "checked" : ""}>제목
                                <input type="checkbox" name="types" value="w" ${pageRequestDTO.checkType("w") ? "checked" : ""}>작성자
                                <input type="text" name="keyword" class="form-control" value='<c:out value="${pageRequestDTO.keyword}" />'/>
                            </div>
                            <div class="input-group mb-3 dueDateDiv">
                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}" />
                                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}" />
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
    </div>
    <div class="row content">
        <div class="row content">
            <div class="card">
                <div class="card-header">
                    Featured
                </div>
                <div class="card-body">
                    <h5 class="card-title">Special title treatment</h5>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">no</th>
                            <th scope="col">Title</th>
                            <th scope="col">Writer</th>
                            <th scope="col">DueDate</th>
                            <th scope="col">Finished</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${responseDTO.dtoList}" var="dto">
                            <tr>
                                <th scope="row"><c:out value="${dto.no}" /></th>
                                <td><a href="/todo/read?no=${dto.no}&${pageRequestDTO.link}" class="text-decoration-none" data-no="${dto.no}"><c:out value="${dto.title}" /></a></td>
                                <td><c:out value="${dto.writer}" /></td>
                                <td><c:out value="${dto.dueDate}" /></td>
                                <td><c:out value="${dto.finished}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="float-end">
                        <ul class="pagination flex-wrap">
                            <c:if test="${responseDTO.prev}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.start - 1}">Previous</a>
                                </li>
                            </c:if>

                            <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                <li class="page-item ${responseDTO.page == num ? "active" : ""}">
                                    <a class="page-link" data-num="${num}">${num}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${responseDTO.next}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.end + 1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                    <script>
                        document.querySelector(".pagination").addEventListener("click", function (e) {
                            e.preventDefault()
                            e.stopPropagation()

                            const target = e.target

                            if(target.tagName !== 'A') {
                                return
                            }
                            const num = target.getAttribute("data-num")

                            const formObj = document.querySelector("#searchForm")

                            formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`

                            formObj.submit();

                        },false)

                        document.querySelector(".clearBtn").addEventListener("click", function(e) {
                            e.preventDefault()
                            e.stopPropagation()

                            self.location = `/todo/list`
                        })
                    </script>
                </div>
            </div>
        </div>
    </div>
    <div class="row footer">
        <h1>Footer</h1>

        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>