<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8">
        <title>Login & Registration</title>
      </head>

      <body>

        <div id='container'>
          <div id='register'>
            <h3>Register here!</h3>

            <c:if test="${registerError != null}">
              <p >${registerError}</p>			
            </c:if>
    
            <c:if test="${errors != null}">
              <c:forEach items="${errors}" var="error">
                <p class="red">${error.defaultMessage}</p>
              </c:forEach>		
            </c:if>		
          
            <form:form  action="/register" method="POST" modelAttribute="user">
              <div class="form-group">
                <form:label path="fname">First Name:</form:label>
                <br>
                <form:errors path="fname" cssClass="error" />
                <form:input path="fname" cssClass="form-control" />
              </div>
              <div class="form-group">
                <form:label path="lname">Last Name:</form:label>
                <br>
                <form:errors path="lname" cssClass="error" />
                <form:input path="lname" cssClass="form-control" />
              </div>
              <div class="form-group">
                <form:label path="email">Email:</form:label>
                <br>
                <form:errors path="email" cssClass="error" />
                <form:input path="email" cssClass="form-control" />
              </div>
              <div class="form-group">
                <form:label path="city">City:</form:label>
                <br>
                <form:errors path="city" cssClass="error" />
                <form:input path="city" cssClass="form-control" />
              </div>
              <div class="form-group">
                <form:label path="state">State:</form:label>
                <form:errors path="state" cssClass="error" />
                <form:select path="state" cssClass="form-inline">
                  <option value="" selected="selected">Select a State</option>
                  <option value="AL">Alabama</option>
                  <option value="AK">Alaska</option>
                  <option value="AZ">Arizona</option>
                  <option value="AR">Arkansas</option>
                  <option value="CA">California</option>
                  <option value="CO">Colorado</option>
                  <option value="CT">Connecticut</option>
                  <option value="DE">Delaware</option>
                  <option value="DC">District Of Columbia</option>
                  <option value="FL">Florida</option>
                  <option value="GA">Georgia</option>
                  <option value="HI">Hawaii</option>
                  <option value="ID">Idaho</option>
                  <option value="IL">Illinois</option>
                  <option value="IN">Indiana</option>
                  <option value="IA">Iowa</option>
                  <option value="KS">Kansas</option>
                  <option value="KY">Kentucky</option>
                  <option value="LA">Louisiana</option>
                  <option value="ME">Maine</option>
                  <option value="MD">Maryland</option>
                  <option value="MA">Massachusetts</option>
                  <option value="MI">Michigan</option>
                  <option value="MN">Minnesota</option>
                  <option value="MS">Mississippi</option>
                  <option value="MO">Missouri</option>
                  <option value="MT">Montana</option>
                  <option value="NE">Nebraska</option>
                  <option value="NV">Nevada</option>
                  <option value="NH">New Hampshire</option>
                  <option value="NJ">New Jersey</option>
                  <option value="NM">New Mexico</option>
                  <option value="NY">New York</option>
                  <option value="NC">North Carolina</option>
                  <option value="ND">North Dakota</option>
                  <option value="OH">Ohio</option>
                  <option value="OK">Oklahoma</option>
                  <option value="OR">Oregon</option>
                  <option value="PA">Pennsylvania</option>
                  <option value="RI">Rhode Island</option>
                  <option value="SC">South Carolina</option>
                  <option value="SD">South Dakota</option>
                  <option value="TN">Tennessee</option>
                  <option value="TX">Texas</option>
                  <option value="UT">Utah</option>
                  <option value="VT">Vermont</option>
                  <option value="VA">Virginia</option>
                  <option value="WA">Washington</option>
                  <option value="WV">West Virginia</option>
                  <option value="WI">Wisconsin</option>
                  <option value="WY">Wyoming</option>
                </form:select>
              </div>
              <div class="form-group">
                <form:label path="password">Password:</form:label>
                <br>
                <form:errors path="password" cssClass="error" />
                <form:password path="password" cssClass="form-control" />
              </div>
              <div class="form-group">
                <form:label path="passwordConfirm">Confirm Password:</form:label>
                <br>
                <form:errors path="passwordConfirm" cssClass="error" />
                <form:password path="passwordConfirm" cssClass="form-control" />
              </div>
              <button type="submit" value='Register' class='btn btn-success'>Register!</button>
            </form:form>
          </div>

          <div id='login'>
            <h3>Login here!</h3>
            <p class="error">
              <c:out value="${loginerror}" />
            </p>
            <form action="/login" method='post'>
              <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="Email">
              </div>
              <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
              </div>
              <button type="submit" value='Login' class='btn btn-success'>Login!</button>
            </form>
          </div>
        </div>
      </body>

      </html>