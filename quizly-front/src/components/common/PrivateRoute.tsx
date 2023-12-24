import { Route, Navigate } from "react-router-dom";

export default function PrivateRoute(
  Component: React.FunctionComponent,
  authenticated: boolean,
  props: any
) {
  return (
    <Route
      element={
        authenticated ? (
          <Component {...props} />
        ) : (
          <Navigate replace={true} to="/login" />
        )
      }
    />
  );
}

// export default function PrivateRoute({
//   component: React.,
//   authenticated,
//   ...rest
// }) {
//   return (
//     <Route
//       {...rest}
//       render={(props) =>
//         authenticated ? (
//           <Component {...rest} {...props} />
//         ) : (
//           <Redirect
//             to={{
//               pathname: "/login",
//               state: { from: props.location },
//             }}
//           />
//         )
//       }
//     />
//   );
// }
