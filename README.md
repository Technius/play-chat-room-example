Play Chat Room Example
======================

This is an example chat room webapp written using Scala and the Play Framework.

Communication is handled using WebSockets. The server code is very simple: a singleton pair of Enumerator and Channel is used for pushing messages to all of the Iteratees. See the [controller code](https://github.com/Technius/play-chat-room-example/blob/master/app/controllers/Application.scala) for more information.

Usage
-----
1. Clone the project: ```git clone https://github.com/Technius/play-chat-room-example.git```
2. Open a terminal and change to the directory: ```cd play-chat-room-example```
3. Run the application: ```activator run```
4. In your web browser, go to ```http://localhost:9000/```

License
-------
This software is licensed under the Apache 2 license, quoted below.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with
the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
language governing permissions and limitations under the License.
