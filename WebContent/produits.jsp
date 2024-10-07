<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Produits</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container col-md-10 col-md-offset-1">
        <div class="panel panel-primary">
            <div class="panel-heading">Recherche des produits</div>
            <div class="panel-body">
                <form action="chercher.do" method="get">
                    <div class="form-group">
                        <label for="motCle">Mot Clé:</label>
                        <input type="text" name="motCle" id="motCle" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-primary">Chercher</button>
                </form>
                <table class="table table-bordered">
    <thead>
        <tr>
            <th>ID</th>
            <th>Désignation</th>
            <th>Prix</th>
            <th>Quantité</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${model.produits}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.designation}</td>
                <td>${p.prix}</td>
                <td>${p.quantite}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
                
            </div>
        </div>
    </div>
</body>
</html>
