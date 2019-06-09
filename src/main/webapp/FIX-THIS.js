const NAVBAR = document.getElementById('navbar');
const PAGE_BODY = document.getElementById('app-view');
const DYNAMIC_CSS = document.getElementById('dynamic-css');
let currentUserId = null;

window.onload = function () {
    console.log("Beginning to render the page 3");
    const USER_SERVICE = new UserService();
    const ROUTER = new Router();
    const AUTH_SERVICE = new AuthService(ROUTER);
    const REIMB_SERVICE = new ReimbService();

    const FINANCE_MANAGER_COMPONENT = new FinanceManagerComponent(REIMB_SERVICE, ROUTER);
    const EMPLOYEE_COMPONENT = new EmployeeComponent(REIMB_SERVICE, ROUTER);
    const LOGIN_COMPONENT = new LoginComponent(AUTH_SERVICE, ROUTER);
    const REGISTER_COMPONENT = new RegisterComponent(USER_SERVICE, ROUTER);
    const NAVBAR_COMPONENT = new NavbarComponent(ROUTER);
    const REIMBURSEMENTS_COMPONENT = new ReimbursementsComponent(REIMB_SERVICE, USER_SERVICE, ROUTER);

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

        <div  id="login-box">
            <div  id="loginview">

                <h4>Welcome!</h4>
                <p>Login</p>
                <hr>
                <i class="fas fa-user"></i><input type="text" placeholder="Username" id="username-cred">
                <br>
                <i class="fas fa-unlock-alt"></i><input type="password" placeholder="Password"
                    id="password-cred">
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
        currentUserId = null;
        PAGE_BODY.innerHTML = this.template;
        document.getElementById('login-btn').addEventListener('click', this.login);
        document.getElementById('register-link').addEventListener('click', this.router.fetchComponent('register').render);

        console.log('LoginComponent rendered.');
    }

    login = async () => {

        let username = document.getElementById('username-cred').value;
        let password = document.getElementById('password-cred').value;

        let principal = await this.authService.authenticate(username, password);
        localStorage.setItem('principal', principal);

        console.log('principalIs');
        console.dir(principal);

        if (principal.role == 'manager') {
            this.router.fetchComponent('manager').render();
        } else {
            this.router.fetchComponent('employee').render();
        }

        //   document.getElementById('alert-msg').setAttribute('hidden', true);
    }
}

class NavbarComponent {

    template = `
    <nav class="navbar navbar-expand-md navbar-light">
    <a class="navbar-brand" id="to-dashboard" href="#"><img id="ERSlogo" src="ERSIMAGE.png"> REIMB Inc.</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#app-nav"
        aria-controls="app-nav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="app-nav">
        <ul class="navbar-nav mr-auto">
           
            <li class="nav-item"><a class="nav-link" id="to-login" href="#">Login</a></li>
            <li class="nav-item"><a class="nav-link" id="to-register" href="#">Register</a></li>
            <li class="nav-item"><a class="nav-link" id="to-register" href="#">Logout</a></li>

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
    <br>

    <div class="row justify-content-center">

        <div class="col-md-8">
            <div class="card">
                <div class="card-header text-center">
                    <h2>Register</h2>
                </div>
                <div class="card-body">

                    <div class="form-group row">
                        <label for="user_name"
                            class="col-md-4 col-form-label text-md-right">Username</label>
                        <div class="col-md-6">
                            <input type="text" id="register-username" name="username" maxlength="25"
                                placeholder="Must be 4-25 characters" size="30">
                        </div>
                    </div>

                    <!--need to add styling-->
                    <div class="form-group row">
                        <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                        <div class="col-md-6">
                            <input type="password" id="register-password" name="password" maxlength="25"
                                placeholder="Must be 4-25 characters" size="30">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="full_name" class="col-md-4 col-form-label text-md-right">First
                            Name</label>
                        <div class="col-md-6">
                            <input type="text" id="register-first-name" name="first-name" maxlength="25"
                                placeholder="Must be under 25 letters" size="30">
                        </div>
                    </div>


                    <div class="form-group row">
                        <label for="last_name" class="col-md-4 col-form-label text-md-right">Last
                            Name</label>
                        <div class="col-md-6">
                            <input type="text" id="register-last-name" name="last-name" maxlength="25"
                                placeholder="Must be under 25 letters" size="30">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="email_address" class="col-md-4 col-form-label text-md-right">Email
                            Address</label>
                        <div class="col-md-6">
                            <input type="email" id="register-email" name="email-address" maxlength="255"
                                placeholder="Must be valid email" size="30">
                        </div>
                    </div>

                    <div class="col-md-6">
                        <button id="register-account" class="btn btn-primary">
                            Register
                        </button>
                        <br>
                        <h5 class="btn btn-link" id="back-to-login"><a>Login</a></h5>

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
        document.getElementById("back-to-login").addEventListener("click", this.router.fetchComponent("register").render);
        console.log("Rendering complete.");
    }

    register = async () => {
        let newUser = {
            username: document.getElementById("register-username").value,
            password: document.getElementById("register-password").value,
            firstName: document.getElementById("register-first-name").value,
            lastName: document.getElementById("register-last-name").value,
            email: document.getElementById('register-email').value
        }
        let registeredUser = await this.userService.register(newUser);

        if (registeredUser) {
            console.log("Registration successful");
            this.router.fetchComponent("login").render();
        } else {
            console.log("Registration unsuccessful");
            alert("unsuccessful");
        }
    }
}

class FinanceManagerComponent {

    reimbursementsList = null;


    // <div class="container">
    //     <div class="row">
    //         <table id="employee-reimbursements" class="table table-striped table-bordered"  cellspacing="5" width="100%">

    template =


        `
        <div class="container">
                <br>
                <br>

                <h3 id="Mgrspace"><strong>Manager's Reimbursement Portal</strong></h3>
                <br>
                <div class="row">
                    <table class="table table-hover">

                        <thead>
                            <tr>
                                <th scope="col">Emp ID</th>
                                <th scope="col">Reimb ID</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Date Submitted</th>
                                <th scope="col">Date Resolved</th>
                                <th scope="col">Description</th>
                                <th scope="col">Status</th>
                                <th scope="col">Type</th>
                            </tr>
                        </thead>

                        <tbody id="mgrtablebody">

                        </tbody>

                    </table>
                </div>
            </div>
            <div>
                <input type="number" id="txt-IDnum" class="insert txt-outline-row-EMPID" placeholder="Reimb ID">
                <button type="button" id="approve-btn" class="btn btn-outline-success">Approve</button>
                <button type="button" id="deny-btn" class="btn btn-outline-danger">Deny</button>
            </div>
    `;

    // render = () => {
    //     console.log("Rendering the FinanceManagerComponent template...");
    //     PAGE_BODY.innerHTML = this.template;
    // }

    constructor(ReimbService, router) {
        console.log("Intantiating UserService...");
        this.ReimbService = ReimbService;
        this.router = router;
        console.log("UserService instantiation complete.");
    }

    render = async () => {
        console.log("Rendering RegisterComponent template...");
        PAGE_BODY.innerHTML = this.template;
        document.getElementById("approve-btn").addEventListener("click", this.apprclick);
        document.getElementById("deny-btn").addEventListener("click", this.denclick);

        this.reimbursementsList = await this.ReimbService.getAll();
        for (let i = 0; i < this.reimbursementsList.length; i++) {
            this.appendRow(this.reimbursementsList[i]);
        }
        console.log("Rendering complete.");
    }

    apprclick = async () => {
        let ID = document.getElementById("txt-IDnum").value;
        console.log(ID + 'THE ID IS');
        let updateTix = null;
        for (let i = 0; i < this.reimbursementsList.length; i++) {
            console.dir(this.reimbursementsList[i].id);
            if (this.reimbursementsList[i].id == ID) {
                updateTix = new Ticket(this.reimbursementsList[i].id, this.reimbursementsList[i].amount,
                    this.reimbursementsList[i].submitted, this.reimbursementsList[i].resolved,
                    this.reimbursementsList[i].description, this.reimbursementsList[i].receipt,
                    this.reimbursementsList[i].author, currentUserId,
                    new ReimbursementStatus(2), this.reimbursementsList[i].reimbType);
                break;
            }
            else {
                updateTix = null;
            }
        }

        let returnedReimb = await this.ReimbService.update(updateTix);
        console.log('RETURNED REIMB');
        console.dir(returnedReimb);
        this.render()
        console.log("successful appr click");
    }


    denclick = async () => {
        let ID = document.getElementById("txt-IDnum").value;
        console.log(ID + 'THE ID IS');
        let updateTix = null;
        for (let i = 0; i < this.reimbursementsList.length; i++) {
            console.dir(this.reimbursementsList[i].id);
            if (this.reimbursementsList[i].id == ID) {
                updateTix = new Ticket(this.reimbursementsList[i].id, this.reimbursementsList[i].amount,
                    this.reimbursementsList[i].submitted, this.reimbursementsList[i].resolved,
                    this.reimbursementsList[i].description, this.reimbursementsList[i].receipt,
                    this.reimbursementsList[i].author, currentUserId,
                    new ReimbursementStatus(3), this.reimbursementsList[i].reimbType);
                break;
            }
            else {
                updateTix = null;
            }
        }

        let returnedReimb = await this.ReimbService.update(updateTix);
        console.log('RETURNED REIMB');
        console.dir(returnedReimb);
        this.render()
        console.log("successful appr click");
    }
    appendRow = async (reimb) => {
        console.log('appending row');
        console.dir(reimb);

        //create a Table row element
        let reimbRow = document.createElement("tr");

        //insert HTML data cells into row
        let rowTemplate =
            `           
            <td>${reimb.author}</td>
            <td>${reimb.id}</td>
            <td>${reimb.amount}</td>
            <td>${reimb.submitted}</td>
            <td>${reimb.resolved}</td>
            <td>${reimb.description}</td>
            <td>${reimb.reimbStatus.reimbStatusName}</td>
            <td>${reimb.reimbType.reimbTypeName}</td>`;

        //Insert into row
        reimbRow.innerHTML = rowTemplate

        //Append child row into table element
        let bodyElement = document.getElementById("mgrtablebody")
        bodyElement.appendChild(reimbRow);
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
        
    <h1 class="text-center">REIMB Inc.</h1>
        
    <br>
        
    <h5 class="text-center">Please submit your reimbursement</h5>
        
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

    constructor(ReimbService, userService, router) {
        console.log("Intantiating UserService...");
        this.userService = userService;
        this.router = router;
        this.ReimbService = ReimbService;
        console.log("UserService instantiation complete.");
    }

    render = () => {
        console.log("Rendering RegisterComponent template...");
        PAGE_BODY.innerHTML = this.template;
        document.getElementById("register-reimbursement").addEventListener("click", this.createR);
        document.getElementById("back-to-dashboard").addEventListener("click", this.router.fetchComponent("login").render);
        console.log("Rendering complete.");
    }

    createR = async () => {

        let TypeId = 0;
        switch (document.getElementById('reim-type').value) {
            case 'lodging': TypeId = 1;
                break;
            case 'travel': TypeId = 2;
                break;
            case 'food': TypeId = 3;
                break;
            case 'other': TypeId = 4;
                break;

        }
        let newR = {
            id: 0,
            amount: document.getElementById('reim-amount').value,
            submitted: "06/10/19",
            resolved: "N/A",
            description: document.getElementById('reim-description').value,
            receipt: null,
            author: currentUserId,
            resolver: 1,
            reimbStatus: {
                reimbStatusId: 1,
                reimbStatusName: 'pending'

            },
            reimbType: {
                reimbTypeId: TypeId,
                reimbTypeName: document.getElementById('reim-type').value
            }
        }


        console.log('Submitting reimbursement');
        console.dir(newR);
        let rr = await this.ReimbService.register(newR);
        console.log('received reimb');
        console.dir(rr);
        if (rr)
            this.router.fetchComponent("employee").render();

    }
}

class EmployeeComponent {
    template = `
    <button class="btn btn-primary" id="new-reim-request"> <a>Create New Reimbursement</a></button>
    <br>
    <br>
    <div class="col-lg-10 col-md-10 col-sm-9 col-xs-12">
        <h3><strong><span class="fa fa-dashboard"></span> Your Reimbursement Requests</strong></h3>
        <br>
        <div class="container">
            <div class="row">
                <table id="employee-reimbursements" class="table table-striped table-bordered" cellspacing="5"
                    width="100%">

                    <tr>
                        <th>Emp ID</th>
                        <th>Reimb ID</th>
                        <th>Amount</th>
                        <th>Date Submitted</th>
                        <th>Date Resolved</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Type</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    `;

    render = () => {
        console.log("Rendering the EmployeeComponent template...");
        PAGE_BODY.innerHTML = this.template;
        document.getElementById("new-reim-request").addEventListener("click", this.router.fetchComponent("Reimbursements").render);
        document.getElementById("logout-again").addEventListener("click", this.router.fetchComponent("Reimbursements").render);
        this.createTable();
    }

    constructor(ReimbService, router) {
        console.log("Intantiating UserService...");
        this.ReimbService = ReimbService;
        this.router = router;
        console.log("UserService instantiation complete.");
    }

    createTable = async () => {
        console.log('rendering table');
        let reimbursements = await this.ReimbService.getById(currentUserId);
        for (let i = 0; i < reimbursements.length; i++) {
            this.appendRow(reimbursements[i]);
        }
        console.dir(reimbursements);
    }


    appendRow = async (reimb) => {
        console.log('PASSED INTO THE REIMB');
        console.dir(reimb);

        //create a Table row element
        let reimbRow = document.createElement("tr");

        //insert HTML data cells into row
        let rowTemplate =
            `           
            <td>${reimb.author}</td>
            <td>${reimb.id}</td>
            <td>${reimb.amount}</td>
            <td>${reimb.submitted}</td>
            <td>${reimb.resolved}</td>
            <td>${reimb.description}</td>
            <td>${reimb.reimbStatus.reimbStatusName}</td>
            <td>${reimb.reimbType.reimbTypeName}</td>`;

        //Insert into row
        reimbRow.innerHTML = rowTemplate

        //Append child row into table element
        let tableElement = document.getElementById("employee-reimbursements")
        tableElement.appendChild(reimbRow);


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

        let response = await fetch('users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newUser)
        });

        let reg = await response.json();
        return reg;


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
        currentUserId = data.id;
        console.log('RECEIVED A RESPONSE');
        console.dir(data);
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
        console.log('attempting to get reimbursements');
        let response = await fetch('finance', {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }

        });
        let reimbList = await response.json();
        return reimbList;
    }

    getById = async (id) => {
        console.log('attempting to get reimbursements');
        console.dir(id);
        let response = await fetch('employee?author=' + id, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }

        });
        let reimbList = await response.json();
        return reimbList;

    }

    getByUsername = async (username) => {
        console.log('UserService.getByUsername() not implemented');
    }

    register = async (newReimb) => {
        console.log('attempting to add ticket');
        console.dir(newReimb);
        let response = await fetch('employee', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newReimb)
        });
        let createdReimb = await response.json();
        return createdReimb;
    }

    update = async (reimb) => {
        console.log('DSGVHGDEHJEVD^&&#^&#%^(@EE#*&');
        console.dir(reimb);
        let response = await fetch('finance', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(reimb)
        });
        let createdReimb = await response.json();
        return createdReimb;
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

class Ticket {
    constructor(id, amount, submitted, resolved, description, receipt, author, resolver, reimbStatus, reimbType) {

        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.reimbStatus = reimbStatus;
        this.reimbType = reimbType;
    }



}

class ReimbursementStatus {
    constructor(reimbStatusId) {
        switch (reimbStatusId) {
            case 1:
                this.reimbStatusId = 1;
                this.reimbStatusName = 'pending';
                break;
            case 2:
                this.reimbStatusId = 2;
                this.reimbStatusName = 'approved';
                break;
            case 3:
                this.reimbStatusId = 3;
                this.reimbStatusName = 'denied';
                break;
        }

    }

}
