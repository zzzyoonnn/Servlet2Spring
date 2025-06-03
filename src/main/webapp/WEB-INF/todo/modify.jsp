<%--
  Created by IntelliJ IDEA.
  User: jiyoon
  Date: 2/24/25
  Time: 10:12 PM
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <h1>Header</h1>
        <div class="col">
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Navbar</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
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
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Dropdown
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Action</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
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
    </div>
    <div class="row content">
        <div class="card">
            <div class="card-header">
                Featured
            </div>
            <div class="card-body">
                <form id = "todoForm" action="/todo/modify" method="post">
                    <input type="hidden" name="page" value="${pageRequestDTO.page}" />
                    <input type="hidden" name="size" value="${pageRequestDTO.size}" />

                    <div class="input-group mb-3">
                        <span class="input-group-text">No</span>
                        <input type="text" name="no" class="form-control" value="${dto.no}" readonly/>

                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">Title</span>
                        <input type="text" name="title" class="form-control"
                               value="${dto.title}"/>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">DueDate</span>
                        <input type="date" name="dueDate" class="form-control"
                               value="${dto.dueDate}"/>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">Writer</span>
                        <input type="text" name="writer" class="form-control"
                               value="${dto.writer}" readonly/>
                    </div>

                    <div class="form-check">
                        <label class="form-check-label">
                            Finished &nbsp;
                        </label>
                        <input class="form-check-input" type="checkbox"
                               name="finished" ${dto.finished ? "checked" : ""} />
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
                // @Valid에서 문제가 발생했을 경우
                const serverValid = {}

                <c:forEach items="${errors}" var="error">
                serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
                </c:forEach>

                console.log(serverValidResult)
            </script>

            <script>
                const formObj = document.querySelector("#todoForm");

                document.querySelector(".btn-danger").addEventListener("click", function(e) {
                    e.preventDefault();
                    e.stopPropagation();

                    formObj.action = "/todo/remove";
                    formObj.method = "post";

                    formObj.submit();
                }, false);

                document.querySelector(".btn-primary").addEventListener("click", function (e) {
                    e.preventDefault();
                    e.stopPropagation();

                    formObj.action = "/todo/modify";
                    formObj.method = "post";

                    formObj.submit();
                }, false);

                document.querySelector(".btn-secondary").addEventListener("click", function (e) {
                    e.preventDefault()
                    e.stopPropagation()

                    self.location = `/todo/list?${pageRequestDTO.link}`;
                }, false);
            </script>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
        crossorigin="anonymous"></script>
</body>
</html>