APIPATH=info
APIKEY=iUdbjjiYMA3qnUqqLRnYBUVy4Vd9IVxk
SECRET=4euEW7yBiN9GUSC2XefuWmKf2pBFUr4Feu4wCH43qIrPERkx4fNgbcmQprDyJK4s
TIMESTAMP=`date +%s%N | cut -c -13`
MESSAGE=$TIMESTAMP+$APIPATH
SIGNATURE=`echo -n $MESSAGE | openssl dgst -sha256 -hmac $SECRET -binary | base64`
echo $SIGNATURE

curl -X GET -i \
  -H "Accept: application/json" \
  -H "Content-Type: application/json" \
  -H "x-auth-key: $APIKEY" \
  -H "x-auth-signature: $SIGNATURE" \
  -H "x-auth-timestamp: $TIMESTAMP" \
  https://ascendex.com/api/pro/v1/info