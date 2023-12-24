type RequestOptions = {
  url: string;
  method: string;
  body?: string;
};

type LoginRequest = {
  email: string;
  password: string;
};

type LoginResponse = {
  accessToken: string;
};

type SignupRequest = {
  username: string;
  email: string;
  password: string;
  confirmPassword: string;
};

type emailAvailabilityResponse = {
  available: boolean;
};
