
const supabaseUrl = "https://duyldmxxeqlxbskmnqdf.supabase.co";
const supabaseAnonKey =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR1eWxkbXh4ZXFseGJza21ucWRmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjA4Mjk4NjgsImV4cCI6MjA3NjQwNTg2OH0.MvH7XbmWtFWi8z_7iu403gPu3ynOcPN1niJYhqodsh4";

window.supabaseClient = window.supabase.createClient(
        supabaseUrl,
        supabaseAnonKey
        );
