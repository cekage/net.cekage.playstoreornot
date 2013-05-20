
Google's PlayStore new limitation workaround
============================================

  I'm disappointed when pro apps like FaceBook or DropBox
displays a self-update instead of letting Google PlayStore's
safe mechanism doing the update.
 
  It seems I'm not the only one who find irresponsible the incitation of
removing app installation default security.
Google warns all developers how bad it is in Developer Policies
https://play.google.com/about/developer-content-policy.html

  The idea of this example app is to detect if application was
installed through PlayStore to disable all self-update
mechanism.      

  How it works? The app exec() pm command line with -i parameter
and checks if the installer is com.android.vending.

  Don't mess with mass user security education :)
