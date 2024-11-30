<c:url var="addAction" value="/purchases/add"/>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
            <form:form action="${addAction}" commandName="purchase">
                <table class="table">
                    <c:if test="${!empty purchase.name}">
                        <tr class="bg-info">
                            <td>
                                <form:label path="id">
                                    <spring:message text="ID"/>
                                </form:label>
                            </td>
                            <td class="form-group">
                                <form:input path="id" readonly="true" size="8" disabled="true"/>
                                <form:hidden path="id"/>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            <form:label path="name">
                                <spring:message text="Марка"/>
                            </form:label>
                        </td>
                        <td class="form-group">
                            <form:input path="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="model">
                                <spring:message text="Модель"/>
                            </form:label>
                        </td>
                        <td class="form-group">
                            <form:input path="model"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="amount">
                                <spring:message text="Количество"/>
                            </form:label>
                        </td>
                        <td class="form-group">
                            <form:input path="amount"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <c:if test="${!empty purchase.name}">
                                <input class="btn btn-primary" type="submit"
                                       value="<spring:message text="Изменить заказ"/>"/>
                            </c:if>
                            <c:if test="${empty purchase.name}">
                                <input class="btn btn-primary" type="submit"
                                       value="<spring:message text="Добавить заказ"/>"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>