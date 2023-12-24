import { Menu, MenuProps } from "antd";
import { Header } from "antd/es/layout/layout";
import { Link, useLocation } from "react-router-dom";
import "./AppHeader.css";

export default function AppHeader() {
  let headerItems: MenuProps["items"];
  headerItems = [
    {
      label: <Link to={"/log-in"}>Login</Link>,
      key: "/log-in",
    },
    {
      label: <Link to={"/sign-up"}>Signup</Link>,
      key: "/sign-up",
    },
  ];
  const location = useLocation();
  return (
    <Header className="app-header">
      <div className="container">
        <div className="app-title">
          <Link to={"/"}>Quizly</Link>
        </div>
        <Menu
          className="app-menu"
          mode="horizontal"
          selectedKeys={[location.pathname]}
          style={{ lineHeight: "64px" }}
          items={headerItems}
        ></Menu>
      </div>
    </Header>
  );
}
