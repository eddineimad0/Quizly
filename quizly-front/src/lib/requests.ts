type RequestOptions ={
    url:string;
    method:string;
    body?:string;
}

type LoginRequest = {
    email:string;
    password:string;
}

type SignupRequest = {
    username:string;
    email:string;
    password:string;
    confirmPassword:string;
}