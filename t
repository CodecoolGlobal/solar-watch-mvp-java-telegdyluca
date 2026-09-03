[33mcommit 4564f8db3eb8450276cf3d475892d617ce477941[m[33m ([m[1;36mHEAD[m[33m -> [m[1;32mmain[m[33m, [m[1;31morigin/main[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Merge: 4e992f2 330329b
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Thu Sep 3 15:27:22 2026 +0200

    Merge pull request #6 from CodecoolGlobal/feature/fix-unit-tests
    
    Fix GeocodingService and SunriseSunsetService unit tests broken by configurable API URLs

[33mcommit 330329b454652360ce1e86b056bd6c8ef999a564[m[33m ([m[1;31morigin/feature/fix-unit-tests[m[33m, [m[1;32mfeature/fix-unit-tests[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 15:25:55 2026 +0200

    Fix GeocodingService and SunriseSunsetService unit tests broken by configurable API URLs

[33mcommit 4e992f259350afafb6fe5a38fa2d54d54584fe68[m
Merge: 98eb552 ff73897
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Thu Sep 3 12:32:56 2026 +0200

    Merge pull request #5 from CodecoolGlobal/feature/backend-refactor
    
    Refactor AuthController and extract SunriseSunsetController

[33mcommit ff7389724712e9d72cd007c1ced0db242cc46cef[m[33m ([m[1;31morigin/feature/backend-refactor[m[33m, [m[1;32mfeature/backend-refactor[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 12:27:58 2026 +0200

    Add missing @Transactional to getSunriseSunset_cityAlreadyInDB test

[33mcommit 65c03a0f10f67f63de1fb339ef29ba1aa530bf62[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 12:15:30 2026 +0200

    Extract login logic into UserService

[33mcommit 81f2d03fa1809b0ebbb3d4d575ad50da591f1bca[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 12:03:57 2026 +0200

    Extract user registration logic into UserService

[33mcommit 79b1e82565b7b83f4edcaa1f725799f024d2a04b[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 11:53:37 2026 +0200

    Extract SunriseSunsetController from AuthController

[33mcommit b8c735e88d330a828ed92799154498250ece8af3[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 11:45:46 2026 +0200

    Rename UserController to AuthController and update auth endpoints from user to auth

[33mcommit 98eb55212d715dd1a271bc1599bb844789eefcce[m
Merge: 270dca4 ac358b6
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Thu Sep 3 09:21:15 2026 +0200

    Merge pull request #4 from CodecoolGlobal/feature/advanced-integration-testing
    
    Add in-memory H2 database and MockWebServer to integration tests

[33mcommit ac358b66430fb9b1a3667adc5ba9e6e13cc4d8fe[m[33m ([m[1;31morigin/feature/advanced-integration-testing[m[33m, [m[1;32mfeature/refactor-user-controller[m[33m, [m[1;32mfeature/advanced-integration-testing[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 09:10:24 2026 +0200

    Add MockWebServer to mock external API calls in the sunrise-sunset city-not-in-DB test

[33mcommit 36e27966d5deb7abead02b40f509789f3fefa00b[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 08:07:44 2026 +0200

    Make geocoding and sunrise-sunset API base URLs configurable

[33mcommit 995bb66fad53a57a684f8ce73b7dc4f982a5da1c[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 07:52:36 2026 +0200

    Use in-memory H2 database for integration tests

[33mcommit 270dca41826b942bbb6099643d4ed3c43d88f14a[m
Merge: 8c7a4e2 2bfceac
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Wed Sep 2 16:47:10 2026 +0200

    Merge pull request #3 from CodecoolGlobal/feature/integration-testing
    
    Integration tests for registration login and sunrise-sunset endpoints

[33mcommit 2bfceacb4f0aa1686a62a9d5d87235e20dddd752[m[33m ([m[1;31morigin/feature/integration-testing[m[33m, [m[1;32mfeature/integration-testing[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Sep 2 16:42:39 2026 +0200

    Refactor login and registration tests

[33mcommit 9d61b2616a6f0e8fb8cebea7b505e030af0aac72[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Sep 2 16:26:57 2026 +0200

    Fix register user test to be idempotent by deleting the test user before running

[33mcommit 293d3bd02edcd650e4445a0ec5d9ce43f2df2558[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Sep 2 16:19:15 2026 +0200

    Add integration tests for sunrise-sunset endpoint (city in DB and not in DB)

[33mcommit bd0fbc913b96daad1ec4eddce198bc4a0e6e9ab2[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Tue Sep 1 14:06:12 2026 +0200

    Add integration tests for user registration and login

[33mcommit 8c7a4e2925716ec9f3ac08b2bb0cb932d8ed674d[m
Merge: 366367d 4ac8dbf
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Tue Sep 1 12:03:06 2026 +0200

    Merge pull request #2 from CodecoolGlobal/feature/react-frontend
    
    SolarWatch frontend - registration, login and solar-watch page

[33mcommit 4ac8dbffdb7b8d7418c7819d3da996fcc8319343[m[33m ([m[1;31morigin/feature/react-frontend[m[33m, [m[1;32mfeature/react-frontend[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Tue Sep 1 11:56:12 2026 +0200

    Style all pages with Behance-inspired weather app design

[33mcommit e174c4ab70bbe2776613f9d8142ac2131d167ded[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 12:19:15 2026 +0200

    Implement solar-watch page

[33mcommit 0ee96111c16a79d78bfa64fb80709e86cc55cdaa[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 11:13:57 2026 +0200

    Implement login page

[33mcommit dbe82ac2673640823670ac1e33747b7c95d0e0c7[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 10:57:47 2026 +0200

    Implement registration page and enable CORS on backend

[33mcommit 15dd76d73a94bf2ea42dfa261305bc71a6630d24[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 09:54:46 2026 +0200

    Add route protection with Protected and GuestsOnly components

[33mcommit a038807a33d2f94fd2b4251a733a7c166da58a3e[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 08:57:36 2026 +0200

    Add basic routing for registration, login and solar-watch pages

[33mcommit 39bc221302611622c2bd7762315fa2ade1901236[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 08:35:55 2026 +0200

    Rename backend module, scaffold React frontend in /frontend

[33mcommit 366367db6583d5eaae580fc0181737b42de15270[m
Merge: e4596ef a78e4bb
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Fri Aug 21 08:04:52 2026 +0200

    Merge pull request #1 from CodecoolGlobal/feature/user-management
    
    Implement user registration, authentication and authorization with JWT

[33mcommit a78e4bb5597ffedcbe0600805a5344ae9c51d54b[m[33m ([m[1;31morigin/feature/user-management[m[33m, [m[1;32mfeature/user-management[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Aug 20 12:28:47 2026 +0200

    Fix missing bean annotations and add exception handling

[33mcommit cb7091c0533e914ea8638893d30daf92c393086f[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Aug 20 11:30:30 2026 +0200

    Implement AdminController

[33mcommit d72fce98e151626acc1865d7b3b7afbe18798336[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Aug 20 10:02:53 2026 +0200

    Implement UserController

[33mcommit 4a52c54c172a56162190c62f6f2874e3f1f6f282[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Aug 20 08:26:02 2026 +0200

    Configure Spring Security with JWT authentication

[33mcommit cbdf13dcb7386d32cd82e1db566886d2e37ac51e[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Aug 19 14:50:05 2026 +0200

    Implement UserDetailsServiceImpl class

[33mcommit 38177211756c401cd50e7cb9df2f57e781de61d4[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Aug 19 14:25:02 2026 +0200

    Implement UserRepository interface

[33mcommit 09d44dc6d3c1355abb51654edf1b7113e55bd255[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Aug 19 14:18:13 2026 +0200

    Implement User entity class

[33mcommit e4596efb7ac96c8e62fcbd1a2e3396706e70971e[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 7 10:45:41 20[33mcommit 4564f8db3eb8450276cf3d475892d617ce477941[m[33m ([m[1;36mHEAD[m[33m -> [m[1;32mmain[m[33m, [m[1;31morigin/main[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Merge: 4e992f2 330329b
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Thu Sep 3 15:27:22 2026 +0200

    Merge pull request #6 from CodecoolGlobal/feature/fix-unit-tests
    
    Fix GeocodingService and SunriseSunsetService unit tests broken by configurable API URLs

[33mcommit 330329b454652360ce1e86b056bd6c8ef999a564[m[33m ([m[1;31morigin/feature/fix-unit-tests[m[33m, [m[1;32mfeature/fix-unit-tests[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 15:25:55 2026 +0200

    Fix GeocodingService and SunriseSunsetService unit tests broken by configurable API URLs

[33mcommit 4e992f259350afafb6fe5a38fa2d54d54584fe68[m
Merge: 98eb552 ff73897
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Thu Sep 3 12:32:56 2026 +0200

    Merge pull request #5 from CodecoolGlobal/feature/backend-refactor
    
    Refactor AuthController and extract SunriseSunsetController

[33mcommit ff7389724712e9d72cd007c1ced0db242cc46cef[m[33m ([m[1;31morigin/feature/backend-refactor[m[33m, [m[1;32mfeature/backend-refactor[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 12:27:58 2026 +0200

    Add missing @Transactional to getSunriseSunset_cityAlreadyInDB test

[33mcommit 65c03a0f10f67f63de1fb339ef29ba1aa530bf62[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 12:15:30 2026 +0200

    Extract login logic into UserService

[33mcommit 81f2d03fa1809b0ebbb3d4d575ad50da591f1bca[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 12:03:57 2026 +0200

    Extract user registration logic into UserService

[33mcommit 79b1e82565b7b83f4edcaa1f725799f024d2a04b[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 11:53:37 2026 +0200

    Extract SunriseSunsetController from AuthController

[33mcommit b8c735e88d330a828ed92799154498250ece8af3[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 11:45:46 2026 +0200

    Rename UserController to AuthController and update auth endpoints from user to auth

[33mcommit 98eb55212d715dd1a271bc1599bb844789eefcce[m
Merge: 270dca4 ac358b6
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Thu Sep 3 09:21:15 2026 +0200

    Merge pull request #4 from CodecoolGlobal/feature/advanced-integration-testing
    
    Add in-memory H2 database and MockWebServer to integration tests

[33mcommit ac358b66430fb9b1a3667adc5ba9e6e13cc4d8fe[m[33m ([m[1;31morigin/feature/advanced-integration-testing[m[33m, [m[1;32mfeature/refactor-user-controller[m[33m, [m[1;32mfeature/advanced-integration-testing[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 09:10:24 2026 +0200

    Add MockWebServer to mock external API calls in the sunrise-sunset city-not-in-DB test

[33mcommit 36e27966d5deb7abead02b40f509789f3fefa00b[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 08:07:44 2026 +0200

    Make geocoding and sunrise-sunset API base URLs configurable

[33mcommit 995bb66fad53a57a684f8ce73b7dc4f982a5da1c[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Sep 3 07:52:36 2026 +0200

    Use in-memory H2 database for integration tests

[33mcommit 270dca41826b942bbb6099643d4ed3c43d88f14a[m
Merge: 8c7a4e2 2bfceac
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Wed Sep 2 16:47:10 2026 +0200

    Merge pull request #3 from CodecoolGlobal/feature/integration-testing
    
    Integration tests for registration login and sunrise-sunset endpoints

[33mcommit 2bfceacb4f0aa1686a62a9d5d87235e20dddd752[m[33m ([m[1;31morigin/feature/integration-testing[m[33m, [m[1;32mfeature/integration-testing[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Sep 2 16:42:39 2026 +0200

    Refactor login and registration tests

[33mcommit 9d61b2616a6f0e8fb8cebea7b505e030af0aac72[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Sep 2 16:26:57 2026 +0200

    Fix register user test to be idempotent by deleting the test user before running

[33mcommit 293d3bd02edcd650e4445a0ec5d9ce43f2df2558[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Sep 2 16:19:15 2026 +0200

    Add integration tests for sunrise-sunset endpoint (city in DB and not in DB)

[33mcommit bd0fbc913b96daad1ec4eddce198bc4a0e6e9ab2[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Tue Sep 1 14:06:12 2026 +0200

    Add integration tests for user registration and login

[33mcommit 8c7a4e2925716ec9f3ac08b2bb0cb932d8ed674d[m
Merge: 366367d 4ac8dbf
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Tue Sep 1 12:03:06 2026 +0200

    Merge pull request #2 from CodecoolGlobal/feature/react-frontend
    
    SolarWatch frontend - registration, login and solar-watch page

[33mcommit 4ac8dbffdb7b8d7418c7819d3da996fcc8319343[m[33m ([m[1;31morigin/feature/react-frontend[m[33m, [m[1;32mfeature/react-frontend[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Tue Sep 1 11:56:12 2026 +0200

    Style all pages with Behance-inspired weather app design

[33mcommit e174c4ab70bbe2776613f9d8142ac2131d167ded[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 12:19:15 2026 +0200

    Implement solar-watch page

[33mcommit 0ee96111c16a79d78bfa64fb80709e86cc55cdaa[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 11:13:57 2026 +0200

    Implement login page

[33mcommit dbe82ac2673640823670ac1e33747b7c95d0e0c7[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 10:57:47 2026 +0200

    Implement registration page and enable CORS on backend

[33mcommit 15dd76d73a94bf2ea42dfa261305bc71a6630d24[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 09:54:46 2026 +0200

    Add route protection with Protected and GuestsOnly components

[33mcommit a038807a33d2f94fd2b4251a733a7c166da58a3e[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 08:57:36 2026 +0200

    Add basic routing for registration, login and solar-watch pages

[33mcommit 39bc221302611622c2bd7762315fa2ade1901236[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 21 08:35:55 2026 +0200

    Rename backend module, scaffold React frontend in /frontend

[33mcommit 366367db6583d5eaae580fc0181737b42de15270[m
Merge: e4596ef a78e4bb
Author: telegdyluca <telegdyluca@gmail.com>
Date:   Fri Aug 21 08:04:52 2026 +0200

    Merge pull request #1 from CodecoolGlobal/feature/user-management
    
    Implement user registration, authentication and authorization with JWT

[33mcommit a78e4bb5597ffedcbe0600805a5344ae9c51d54b[m[33m ([m[1;31morigin/feature/user-management[m[33m, [m[1;32mfeature/user-management[m[33m)[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Aug 20 12:28:47 2026 +0200

    Fix missing bean annotations and add exception handling

[33mcommit cb7091c0533e914ea8638893d30daf92c393086f[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Aug 20 11:30:30 2026 +0200

    Implement AdminController

[33mcommit d72fce98e151626acc1865d7b3b7afbe18798336[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Aug 20 10:02:53 2026 +0200

    Implement UserController

[33mcommit 4a52c54c172a56162190c62f6f2874e3f1f6f282[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Thu Aug 20 08:26:02 2026 +0200

    Configure Spring Security with JWT authentication

[33mcommit cbdf13dcb7386d32cd82e1db566886d2e37ac51e[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Aug 19 14:50:05 2026 +0200

    Implement UserDetailsServiceImpl class

[33mcommit 38177211756c401cd50e7cb9df2f57e781de61d4[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Aug 19 14:25:02 2026 +0200

    Implement UserRepository interface

[33mcommit 09d44dc6d3c1355abb51654edf1b7113e55bd255[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Wed Aug 19 14:18:13 2026 +0200

    Implement User entity class

[33mcommit e4596efb7ac96c8e62fcbd1a2e3396706e70971e[m
Author: Telegdy Luca <telegdyluca@gmail.com>
Date:   Fri Aug 7 10:45:41 20