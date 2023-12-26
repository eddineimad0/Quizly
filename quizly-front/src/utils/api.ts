import { API_BASE_URL, QUIZ_LIST_SIZE, ACCESS_TOKEN } from "../constants";
import { UserProfile } from "../lib/user";

const request = (options: RequestOptions): Promise<any> => {
  const headers: HeadersInit = [["Content-Type", "application/json"]];

  if (localStorage.getItem(ACCESS_TOKEN)) {
    headers.push([
      "Authorization",
      `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`,
    ]);
  }

  const url: URL = new URL(options.url);
  const cfg: RequestInit = {
    headers: headers,
    method: options.method,
    body: options.body,
  };
  console.log(url, cfg);

  return fetch(url, cfg).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};

export function getAllPolls(page: number, size: number) {
  page = page || 0;
  size = size || QUIZ_LIST_SIZE;

  return request({
    url: API_BASE_URL + "/polls?page=" + page + "&size=" + size,
    method: "GET",
  });
}

export function createQuiz(quizData: Quiz): Promise<Response> {
  return request({
    url: API_BASE_URL + "/quizes",
    method: "POST",
    body: JSON.stringify(quizData),
  });
}

// export function castVote(voteData) {
//     return request({
//         url: API_BASE_URL + "/polls/" + voteData.pollId + "/votes",
//         method: 'POST',
//         body: JSON.stringify(voteData)
//     });
// }

export function login(loginRequest: LoginRequest): Promise<LoginResponse> {
  return request({
    url: API_BASE_URL + "/auth/sign-in",
    method: "POST",
    body: JSON.stringify(loginRequest),
  });
}

export function signup(signupRequest: SignupRequest) {
  return request({
    url: API_BASE_URL + "/auth/sign-up",
    method: "POST",
    body: JSON.stringify(signupRequest),
  });
}

export function checkEmailAvailability(
  email: string
): Promise<emailAvailabilityResponse> {
  return request({
    url: API_BASE_URL + "/users/checkEmailAvailability?email=" + email,
    method: "GET",
  });
}

export function getCurrentUser(): Promise<UserProfile> {
  if (!localStorage.getItem(ACCESS_TOKEN)) {
    return Promise.reject("No access token set.");
  }

  return request({
    url: API_BASE_URL + "/user/me",
    method: "GET",
  });
}

export function getUserProfile(username: string): Promise<UserProfile> {
  return request({
    url: API_BASE_URL + "/users/" + username,
    method: "GET",
  });
}

export function getUserCreatedPolls(
  username: string,
  page: number,
  size: number
) {
  page = page || 0;
  size = size || QUIZ_LIST_SIZE;

  return request({
    url:
      API_BASE_URL +
      "/users/" +
      username +
      "/polls?page=" +
      page +
      "&size=" +
      size,
    method: "GET",
  });
}

export function getUserVotedPolls(
  username: string,
  page: number,
  size: number
) {
  page = page || 0;
  size = size || QUIZ_LIST_SIZE;

  return request({
    url:
      API_BASE_URL +
      "/users/" +
      username +
      "/votes?page=" +
      page +
      "&size=" +
      size,
    method: "GET",
  });
}
