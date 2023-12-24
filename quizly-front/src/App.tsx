import { Layout } from "antd";
import "./App.css";
import AppHeader from "./components/common/AppHeader";
import { Content } from "antd/es/layout/layout";
import { Route, Routes } from "react-router-dom";
import NewQuiz from "./components/quiz/NewQuiz";
import SignUp from "./components/signup/signup";
import Login from "./components/login/login";

function App() {
  return (
    <Layout className="app-container">
      <AppHeader />
      <Content className="app-content">
        <div className="container">
          <Routes>
            <Route path="/" element={<NewQuiz />} />
            <Route path="/sign-up" element={<SignUp />} />
            <Route path="/log-in" element={<Login />} />
          </Routes>
        </div>
      </Content>
    </Layout>
  );
}

export default App;
