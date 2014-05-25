UPDATE ACCOUNT 
SET
  EMAIL =/*account.email*/'email',
  FIRSTNAME =/*account.firstName*/'firstname',
  LASTNAME =/*account.lastName*/'lastname',
  STATUS =/*account.status*/'ok',
  ADDR1 =/*account.address1*/'address1',
  ADDR2 =/*account.address2*/'address2',
  CITY =/*account.city*/'city',
  STATE =/*account.state*/'state',
  ZIP =/*account.zip*/'zip',
  COUNTRY =/*account.country*/'country',
  PHONE =/*account.phone*/'phone'
WHERE 
  USERID =/*account.username*/'username'