# JavaEE Student Project
<ul>
<li>
What we need(software):
  <ul>
    <li>IntelliJ</li>
    <li>PostrgreSQL(pgAdmin 4)</li>
  </ul>
</li>
<li>
What we add(dependency):
  <ul>
    <li>PosgreSQL</li>
    <li>Jstl</li>
    <li>jetty</li>
    <li>comons-fileupload</li>
  </ul>
</li>

<li>Create a postgre database, settings for that we will use after, like db:url, username, password</li>
<li>For DB connection we use sql package thats includes libraries Connection, DriverManager, etc.</li>
<li>Create a new package utils and add new class Settings(Connection)</li>
<li>Create new method Connection getConnection()</li>
<li>Create all moddels for each entity and getters,setters fot them</li>
<li>Include this method in each DAO objects methods example : prepareStatement = Settings.getConnection().prepareStatement("sql code")
DAO classes contains sql querries for every logic that we create in controller</li>
<li>After creating DAO classes we need to create controllers for each model and initialize DAO objects there</li>
<li>Each controller have doGet and doPost methods that processed data</li>
<li>Create views for models that we'll need to process</li>
</ul>
