/*
파일 상단에 import할 것!
import android.content.pm.Signature;
*/

private void getHashKey(){
  PackageInfo packageInfo = null;
  try {
      packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
  } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
  }
  if (packageInfo == null)
      Log.e("KeyHash", "KeyHash:null");

  for (Signature signature : packageInfo.signatures) {
      try {
          MessageDigest md = MessageDigest.getInstance("SHA");
          md.update(signature.toByteArray());
          Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
      } catch (NoSuchAlgorithmException e) {
          Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
      }
  }
}
