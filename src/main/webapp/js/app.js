const NAVBAR = document.getElementById('navbar');
const PAGE_BODY = document.getElementById('app-view');
const DYNAMIC_CSS = document.getElementById('dynamic-css');

window.onload = function () {
    console.log("Beginning to render the page 0");
    const USER_SERVICE = new UserService();
    const ROUTER = new Router();
    const AUTH_SERVICE = new AuthService(ROUTER);
  //  const ReimbService = new ReimbService();

    const FINANCE_MANAGER_COMPONENT = new FinanceManagerComponent();
    const EMPLOYEE_COMPONENT = new EmployeeComponent(ReimbService, ROUTER);
    const LOGIN_COMPONENT = new LoginComponent(AUTH_SERVICE, ROUTER);
    const REGISTER_COMPONENT = new RegisterComponent(USER_SERVICE, ROUTER);
    const NAVBAR_COMPONENT = new NavbarComponent(ROUTER);
    const REIMBURSEMENTS_COMPONENT = new ReimbursementsComponent(ROUTER);

    console.log("Components loaded");

    console.log("Populating routes");
    ROUTER.addRoute("login", LOGIN_COMPONENT);
    ROUTER.addRoute("register", REGISTER_COMPONENT);
    ROUTER.addRoute("manager", FINANCE_MANAGER_COMPONENT);
    ROUTER.addRoute("employee", EMPLOYEE_COMPONENT);
    ROUTER.addRoute("Reimbursements", REIMBURSEMENTS_COMPONENT);


    if (LOGIN_COMPONENT && REGISTER_COMPONENT && FINANCE_MANAGER_COMPONENT && EMPLOYEE_COMPONENT) {


        console.log("Routes populated.")

        NAVBAR_COMPONENT.render();
        LOGIN_COMPONENT.render();
    }
}
//------------------------------------------------------------------
// Components

class LoginComponent {

    template =
        `<div class="container">

  <div class="box-border" id="login-box">
      <div class="box-body">
          
              <h3>Login</h3>
          <p>Sign in to an existing account:</p>
          <hr>
              <i class="fas fa-user"></i><input type="text" placeholder="Username" id="username-cred">
              <br>
              <i class="fas fa-unlock-alt"></i><input type="password" placeholder="Password" id="password-cred">
              <br>
          <button id="login-btn">Login&nbsp;<i class="fas fa-arrow-right"></i></button>
          <br>
          <a href="#" id="register-link">Register a new account</a>
      </div>
  </div>

</div> 
`;

    constructor(authService, router) {
        console.log('Instantiating LoginComponent');
        this.authService = authService;
        this.router = router;
        console.log('Instantiation of LoginComponent complete.')
    }

    render = () => {
        console.log('Rendering LoginComponent template...');
        PAGE_BODY.innerHTML = this.template;
        document.getElementById('login-btn').addEventListener('click', this.login);
        document.getElementById('register-link').addEventListener('click', this.router.fetchComponent('register').render);

        console.log('LoginComponent rendered.');
    }

    login = async () => {

        let username = document.getElementById('username-cred').value;
        let password = document.getElementById('password-cred').value;

        localStorage.setItem('principal', await this.authService.authenticate(username, password));


        //   document.getElementById('alert-msg').setAttribute('hidden', true);
        this.router.fetchComponent('employee').render();

    }
}

//   

class NavbarComponent {

    template = `
  <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <a class="navbar-brand" id="to-dashboard" href="#">REIMB Inc.</a>
      
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#app-nav" aria-controls="app-nav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
      </button>
  
      <div class="collapse navbar-collapse" id="app-nav">
          <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                  <a class="nav-link" href="#" id="toHome">Home<span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item dropdown"><a
                  class="nav-link dropdown-toggle" id="dropdown01" 
                  data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="true"><span id="dropdown-label">Pages</span></a>
                  <div class="dropdown-menu" id="nav-dropdown" aria-labelledby="dropdown01">
                      <a class="dropdown-item" id="to-login" href="#">Login</a> 
                      <a class="dropdown-item" id="to-register" href="#">Register</a>
                  </div>
              </li>
          </ul>

      </div>
  </nav>
  `;

    // constructor(LoginComponent, RegisterComponent) {
    constructor(router) {
        console.log("Instantiating NavbarComponent...");
        this.router = router;
        console.log("NavBar component instantiation complete.");
    }

    render = () => {
        console.log("Rendering NavbarComponent template...");
        NAVBAR.innerHTML = this.template;
        // document.getElementById("toHome").addEventListener("click", this.router.fetchComponent("login").render)
        // document.getElementById("nav-pending").addEventListener("click", this.router.fetchComponent("manager").render);
        document.getElementById("to-login").addEventListener("click", this.router.fetchComponent("login").render);
        document.getElementById("to-register").addEventListener("click", this.router.fetchComponent("register").render);
        console.log("Rendering complete.");
    }
}

class RegisterComponent {

    template = `
  <div class="container">
    
  <br>

  <br>
      
  <h1 class="text-center">REIMB INC.</h1>
      
  <br>
      
  <h6 class="text-center">REIMB INC.</h6>
      
  <br>
      
  <br>

  <div class="row justify-content-center">
    
      <div class="col-md-8">
          <div class="card" >
              <div class="card-header">Register</div>
              <div class="card-body">
                  
                      <div class="form-group row">
                          <label for="user_name" class="col-md-4 col-form-label text-md-right">Username</label>
                          <div class="col-md-6">
                              <input type="text" id="register-username"  name="username" maxlength="25" placeholder="Must be 4-25 characters" size="30">
                          </div>
                      </div>

                      <!--need to add styling-->
                      <div class="form-group row">
                          <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                          <div class="col-md-6">
                              <input type="password" id="register-password"  name="password" maxlength="25" placeholder="Must be 4-25 characters" size="30">
                          </div>
                      </div>

                      <div class="form-group row">
                          <label for="full_name" class="col-md-4 col-form-label text-md-right">First Name</label>
                          <div class="col-md-6">
                              <input type="text" id="register-first-name"  name="first-name" maxlength="25" placeholder="Must be under 25 letters" size="30">
                          </div>
                      </div>


                      <div class="form-group row">
                          <label for="last_name" class="col-md-4 col-form-label text-md-right">Last Name</label>
                          <div class="col-md-6">
                              <input type="text" id="register-last-name"  name="last-name" maxlength="25" placeholder="Must be under 25 letters" size="30">
                          </div>
                      </div>

                      <div class="form-group row">
                          <label for="email_address" class="col-md-4 col-form-label text-md-right">Email Address</label>
                          <div class="col-md-6">
                              <input type="email" id="register-email" name="email-address" maxlength="255" placeholder="Must be valid email" size="30">
                          </div>
                      </div>

                      <div class="col-md-6 offset-md-4">
                          <button id="register-account" class="btn btn-primary">
                              Register
                          </button>
                          <br>
                          <h5 class="btn btn-link"><a id="back-to-login">Back to Login page</a></h5>
                       
                          <div class="text-center" id="registration-success" role="alert">
                              Registration successful. Redirecting to Login page.
                          </div>

                      </div>
              </div>
          </div>
      </div>
  </div>
</div>
`;

    constructor(userService, router) {
        console.log("Intantiating UserService...");
        this.userService = userService;
        this.router = router;
        console.log("UserService instantiation complete.");
    }

    render = () => {
        console.log("Rendering RegisterComponent template...");
        PAGE_BODY.innerHTML = this.template;
        document.getElementById("register-account").addEventListener("click", this.register);
        document.getElementById("back-to-login").addEventListener("click", this.router.fetchComponent("login").render);
        console.log("Rendering complete.");
    }

    register = () => {
        let newUser = {
            username: document.getElementById("username-input").value,
            password: document.getElementById("password-input").value,
            firstName: document.getElementById("first-name-input").value,
            lastName: document.getElementById("last-name-input").value,
            email: document.getElementById('email-input').value
        }
        let registeredUser = this.userService.register(newUser);

        if (registeredUser) {
            console.log("Registration successful");
            this.router.fetchComponent("login").render();
        } else {
            console.log("Registration unsuccessful");
        }
    }
}

class FinanceManagerComponent {

    template = `
  <table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
      <td>
              <button type="button" class="btn btn-outline-success">Approve</button>
          </td>
      <td>
              <button type="button" class="btn btn-outline-danger">Deny</button>
          </td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td colspan="2">Larry the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
  `;

    render = () => {
        console.log("Rendering the FinanceManagerComponent template...");
        PAGE_BODY.innerHTML = this.template;
    }

    constructor(userService, router) {
        console.log("Intantiating UserService...");
        this.userService = userService;
        this.router = router;
        console.log("UserService instantiation complete.");
    }

    render = () => {
        console.log("Rendering RegisterComponent template...");
        PAGE_BODY.innerHTML = this.template;
        document.getElementById("register-account").addEventListener("click", this.register);
        document.getElementById("back-to-login").addEventListener("click", this.router.fetchComponent("login").render);
        console.log("Rendering complete.");
    }
}


class RegistrationComponent {

    template = `
      <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">First</th>
          <th scope="col">Last</th>
          <th scope="col">Handle</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">1</th>
          <td>Mark</td>
          <td>Otto</td>
          <td>@mdo</td>
          <td>
                  <button type="button" class="btn btn-outline-success">Approve</button>
              </td>
          <td>
                  <button type="button" class="btn btn-outline-danger">Deny</button>
              </td>
        </tr>
        <tr>
          <th scope="row">2</th>
          <td>Jacob</td>
          <td>Thornton</td>
          <td>@fat</td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td colspan="2">Larry the Bird</td>
          <td>@twitter</td>
        </tr>
      </tbody>
    </table>
      `;
    
        render = () => {
            console.log("Rendering the FinanceManagerComponent template...");
            PAGE_BODY.innerHTML = this.template;
        }
    
        constructor(userService, router) {
            console.log("Intantiating UserService...");
            this.userService = userService;
            this.router = router;
            console.log("UserService instantiation complete.");
        }
    
        render = () => {
            console.log("Rendering RegisterComponent template...");
            PAGE_BODY.innerHTML = this.template;
            document.getElementById("register-account").addEventListener("click", this.register);
            document.getElementById("back-to-login").addEventListener("click", this.router.fetchComponent("login").render);
            console.log("Rendering complete.");
        }
    }

class ReimbursementsComponent {

    template = `
    <div class="cotainer">

    <br>

    <br>
        
    <h1 class="text-center">????????</h1>
        
    <br>
        
    <h6 class="text-center">??????????</h6>
        
    <br>
        
    <br>
    
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Create New Reimbursement</div>
                <div class="card-body">

                    <div class="form-group row">
                        
                        <label for="Amount">Amount</label>
                        <input type="text" class="form-control" class="col-md-4 col-form-label text-md-right" id="reim-amount"
                            name="amount" placeholder="Enter total expenses for this request.">
                    </div>
                    <hr />
                    <div class="form-group row">
                        
                        <label for="description">Description</label>
                        <textarea class="form-control" id="reim-description" name="description" rows="5" placeholder="Please describe expenses in 10 to 250 characters."></textarea>
                    </div>
                    <hr />
                    <div class="form-group row">
                        
                        <label for="type">Type of Expense</label>
                        <select class="form-control" class="col-md-4 col-form-label text-md-right" id="reim-type"
                            name="type">
                            <option value="lodging">Lodging</option>
                            <option value="travel">Travel</option>
                            <option value="food">Food</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <hr />
                    <div class="col-md-6 offset-md-4">
                        <button id="register-reimbursement" class="btn btn-primary">
                            Submit New Reimbursement Request
                        </button>
                        <br>
                        <h5 class="btn btn-link"><a id="back-to-dashboard">Back to Dashboard</a></h5>
                    </div>

                    <div>
                        <div class="alert alert-danger text-center" id="alert-msg-reimbursement" role="alert">
                            Please fill all fields.
                        </div>
                        <div class="text-center" id="reimbursement-success" role="alert">
                            Submission successful. Redirecting to your Dashboard.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>    
        `;
    
    // render = () => {
    //     console.log("Rendering the FinanceManagerComponent template...");
    //     PAGE_BODY.innerHTML = this.template;
    // }

    constructor(userService, router) {
        console.log("Intantiating UserService...");
        this.userService = userService;
        this.router = router;
        console.log("UserService instantiation complete.");
    }

    render = () => {
        console.log("Rendering RegisterComponent template...");
        PAGE_BODY.innerHTML = this.template;
        document.getElementById("register-account").addEventListener("click", this.register);
        document.getElementById("back-to-login").addEventListener("click", this.router.fetchComponent("login").render);
        console.log("Rendering complete.");
    }
}

class EmployeeComponent {
    template = `
    <div id="top-nav" class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <h5 class="navbar-brand">Welcome to REIMB INC.</h5>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a id = "logout"><i class="fa fa-sign-out" class="pointer"></i> Logout</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
        <ul class="nav nav-pills nav-stacked" style="border-right:1px solid rgb(140, 145, 146)">
            <!--<li class="nav-header"></li>-->
            <li><a id= "new-reim-request">Create New Reimbursement</a></li>
            <li><a id = "logout-again"><i class="fa fa-lock" class="pointer"></i> Logout</a></li>
        </ul>
    </div><!-- /span-3 -->
    <div class="col-lg-10 col-md-10 col-sm-9 col-xs-12">
        <!-- Right -->
        <h1><strong><span class="fa fa-dashboard"></span> Your Reimbursement Requests</strong></h1>
        <hr>
        <div class="container">
            <div class="row">
                <table id="employee-reimbursements" class="table table-striped table-bordered"  cellspacing="5" width="100%">
                    <thead >
                        <tr>
                            <th>Reimb ID</th>
                            <th>Amount</th>
                            <th>Date Submitted</th>
                            <th>Date Resolved</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Type</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>

    `;

    render = () => {
        console.log("Rendering the EmployeeComponent template...");
        PAGE_BODY.innerHTML = this.template;
        document.getElementById("new-reim-request").addEventListener("click", this.router.fetchComponent("Reimbursements").render);
    }

    constructor(ReimbService, router) {
        console.log("Intantiating UserService...");
        this.ReimbService = ReimbService;
        this.router = router;
        console.log("UserService instantiation complete.");
    }

    getReimbursements = async () => {
        let response = await fetch('employee', {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(credentials)
        }).catch(err => {
            console.log("[ERROR] - Authentication unsuccessful")
        });

        for (let reimbs = 0; reimbs < response.principal.length; reimbs++) {

        }
    }
}

//------------------------------------------------------------------------------------

// Services

class UserService {

    currentUser;

    constructor() {
        console.log('Instantiating UserService...');
        this.currentUser = new User();
        console.log('UserService instantiation complete.');
    }

    getAll = async () => {
        let response = await fetch("users", {
            method: "GET",
            headers: {
                "Principal": localStorage.getItem("jwt")
            }
        })
    }

    //////////////////////////////////////// TO DO ////////////////////////////////////////////////////////////////////
    getById = async (id) => {
        console.log('UserService.getById() not implemented');
    }

    getByUsername = async (username) => {
        console.log('UserService.getByUsername() not implemented');
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    register = async (newUser) => {
        let response = await fetch('register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newUser)
        });


    }
}

class AuthService {

    currentUser;

    constructor() {
        console.log('Instantiating AuthService...');
        this.currentUser = new User();
        console.log('AuthService instantiation complete.');
    }

    authenticate = async (username, password) => {
        let credentials = {
            username: username,
            password: password
        }

        let response = await fetch('auth', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(credentials)

        }).catch(err => {
            console.log("[ERROR] - Authentication unsuccessful")
        });

        localStorage.setItem("jwt", response.headers.get("Authorization"));

        let data = await response.json();
        this.currentUser = data;

        return data;
    
        }

        logout = () => {
            localStorage.removeItem("jwt");
    }
}

//------------------------------------------------------------------------

class ReimbService {

    currentUser;

    constructor() {
        console.log('Instantiating UserService...');
        this.currentUser = new User();
        console.log('UserService instantiation complete.');
    }

    getAll = async () => {
        let response = await fetch("users", {
            method: "GET",
            headers: {
                "Principal": localStorage.getItem("jwt")
            }
        })
    }

    getById = async (id) => {
        console.log('UserService.getById() not implemented');
    }

    getByUsername = async (username) => {
        console.log('UserService.getByUsername() not implemented');
    }

    register = async (newUser) => {
        let response = await fetch('register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newUser)
        });


    }

}
//------------------------------------------------------------------------

// Router

class Router {
    routes = [];

    constructor() {
        console.log("Instantiating Router...")
        console.log("Router instantiation complete.")
    }

    addRoute = (path, component) => {  // the object in parameters is basically a route
        this.routes.push({ path, component });

    }

    fetchComponent = (path) => {
        // if the route's path is equal to the path we want, then pop off that route object 
        console.log('fetching component  ' + path)
        let destinationRoute = this.routes.filter(route => route.path === path).pop();
        return destinationRoute.component;
    }
}
//------------------------------------------------------------------------


//------------------------------------------------------------------------------------

// Models

class User {
    constructor(userId, username, password, firstName, lastName, email, roleId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roleId = roleId;
    }
}

// Function to make navbar responsive
function mobileMenu() {
    const TOP_NAV = document.getElementById("top-nav");
    if (TOP_NAV.className === "top-nav") {
        TOP_NAV.className += " responsive";
    } else {
        TOP_NAV.className = "top-nav";
    }
}




