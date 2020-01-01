package com.yair.noteapp.Model;

/**
 * Created by Yair on 19/12/2019.
 */

import android.app.Application;
import android.os.AsyncTask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yair.noteapp.Entity.Note;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDao noteDao;
    private DatabaseReference notesRef;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        // Write a message to the database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        notesRef = firebaseDatabase.getReference("notes");

        noteDao = database.noteDao();

        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
//        notesRef.push().setValue(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    //region AsyncTask implementation

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Note> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Note doInBackground(Note... notes) {
            long add_id = noteDao.insert(notes[0]);
            notes[0].setId((int)add_id);
            return notes[0];
        }
        @Override
        protected void onPostExecute(Note note){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference notesRef = firebaseDatabase.getReference("notes");;
            notesRef.push().setValue(note);
        }

    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
    //endregion
}