package index;

public class IndexReposne {

    final Integer docId;
    final Integer row;
    final Integer pos;

    public IndexReposne(Integer docId, Integer row, Integer pos) {
        this.docId = docId;
        this.row = row;
        this.pos = pos;
    }

    public Integer getDocId() {
        return docId;
    }

    public Integer getPos() {
        return pos;
    }

    public Integer getRow() {
        return row;
    }

    public boolean after(IndexReposne reposne) {
        return this.docId == reposne.docId
                && this.row == reposne.row
                && this.pos < reposne.pos;
    }
}
