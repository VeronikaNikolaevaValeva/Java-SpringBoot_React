export default function authHeader() {
    const accessTokens = JSON.parse(localStorage.getItem('accessTokens'));
    if (accessTokens && accessTokens.access_token) {
      return accessTokens.access_token;
    } else {
      return {};
    }
  }