/*
    A module is nothing more than a separate JS file that contains logic, usually
    which serves a single purpose. Modules can be exported, which makes them available
    for import in another JS file.

    // ES5 syntax for importing modules
    let NavbarComponent = require('./components/navbar/navbar.component');

    // ES6 syntax for importing modules
    import LoginComponent from './components/login/login.component';

*/

const NAVBAR = document.getElementById('navbar');
const APP_VIEW = document.getElementById('app-view');
const DYNAMIC_CSS = document.getElementById('dynamic-css');

window.onload = function() {

    console.log('Initializing application...');

    console.log('Loading components and services...');
    const USER_SERVICE = new UserService();
    const LOGIN_COMPONENT = new LoginComponent(USER_SERVICE);
    const REGISTER_COMPONENT = new RegisterComponent(USER_SERVICE);
    const NAVBAR_COMPONENT = new NavbarComponent(LOGIN_COMPONENT, REGISTER_COMPONENT);
    console.log('Components and services loaded.');

    NAVBAR_COMPONENT.render();
}

//------------------------------------------------------------------------------------

// Components

class LoginComponent {

    template =`
    <div class="login-form">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="username-cred" class="sr-only">Username</label>
        <input type="text" id="username-cred" class="form-control" placeholder="Username" required autofocus>
        
        <label for="password-cred" class="sr-only">Password</label>
        <input type="password" id="password-cred" class="form-control" placeholder="Password" required>
        
        <button class="btn btn-lg btn-primary btn-block" id="submit-creds">Sign in</button>
        <br>
        <div hidden class="alert alert-danger text-center" id="alert-msg" role="alert">
            Invalid Credentials!
        </div>
        <p class="mt-5 mb-3 text-muted">&copy; Revature 2017-2019</p>
    </div>
    `;

    constructor(userService) {
        console.log('Instantiating LoginComponent');
        this.userService = userService;
        console.log('Instantiation of LoginComponent complete.')
    }

    render = () => {
        console.log('Rendering LoginComponent template...');
        APP_VIEW.innerHTML = this.template;
        DYNAMIC_CSS.setAttribute('href', './css/login.component.css');
        document.getElementById('submit-creds').addEventListener('click', this.login);
        console.log('LoginComponent rendered.');
    }

    login = () => {

        let username = document.getElementById('username-cred').value;
        let password = document.getElementById('password-cred').value;

        let user = this.userService.authenticate(username, password);

        if(user) {
            console.log('Authentication successful!');
            document.getElementById('alert-msg').setAttribute('hidden', true);
        } else {
            console.log('Authentication unsuccessful!');
            document.getElementById('alert-msg').removeAttribute('hidden');
        }
    }
}


class NavbarComponent {
    template =`
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" id="to-dashboard" href="#">RevaBooks</a>
        
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
            <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
    `;

    constructor(loginComponent, registerComponent) {
        console.log('Instantiating NavbarComponent...');
        this.loginComponent = loginComponent;
        this.registerComponent = registerComponent;
        console.log('NavbarComponent instantiation complete.')
    }

    render = () => {
        console.log('Rendering NavbarComponent template...');
        NAVBAR.innerHTML = this.template;
        DYNAMIC_CSS.setAttribute('href', './css/navbar.component.css');
        document.getElementById('to-login').addEventListener('click', this.loginComponent.render);
        document.getElementById('to-register').addEventListener('click', this.registerComponent.render);
        console.log('Rendering complete.')
    }

}

class RegisterComponent {

    template = `
    <div class="register-form">
        <h1 class="h3 mb-3 font-weight-normal">Register a new account</h1>
        <label for="register-fn" class="sr-only">First Name</label>
        <input type="text" id="register-fn" class="form-control" placeholder="First Name" required autofocus>
        
        <label for="register-ln" class="sr-only">Last Name</label>
        <input type="text" id="register-ln" class="form-control" placeholder="Last Name" required autofocus>
        
        <label for="register-username" class="sr-only">Username</label>
        <input type="text" id="register-username" class="form-control" placeholder="Username" required autofocus>
        
        <label for="register-password" class="sr-only">Password</label>
        <input type="password" id="register-password" class="form-control" placeholder="Password" required>
        
        <button class="btn btn-lg btn-primary btn-block" id="register-account">Register</button>
        <p class="mt-5 mb-3 text-muted">&copy; Revature 2017-2019</p>
    </div>
    `;

    constructor(userService) {
        console.log('Instantiating UserService...');
        this.userService = userService;
        console.log('UserService instantiation complete.');
    }

    render = () => {
        console.log('Rendering RegisterComponent template...');
        APP_VIEW.innerHTML = this.template;
        DYNAMIC_CSS.setAttribute('href', './css/register.component.css');
        document.getElementById('register-account').addEventListener('click', this.register);
        console.log('Rendering complete.')
    }

    register = () => {
        console.log('RegisterComponent.register() not implemented!');
        this.userService.register();
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

    getAll = () => {
        console.log('UserService.getAll() not implemented');
    }

    getById = (id) => {
        console.log('UserService.getById() not implemented');
    }

    getByUsername = (username) => {
        console.log('UserService.getByUsername() not implemented');
    }

    register = (newUser) => {
        console.log('UserService.register() not implemented');
    }

    authenticate = (username, password) => {
        console.log('UserService.authenticate() not implemented');
        console.log(`Provided username: ${username}`);
        console.log(`Provided password: ${password}`);
        return null;
    }
}

//------------------------------------------------------------------------------------

// Models

class User {
    constructor(firstName, lastName, username, password, role, id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
    }
}
