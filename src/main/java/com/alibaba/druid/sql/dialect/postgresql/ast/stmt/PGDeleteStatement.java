package com.alibaba.druid.sql.dialect.postgresql.ast.stmt;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.dialect.postgresql.ast.PGWithClause;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class PGDeleteStatement extends SQLDeleteStatement implements PGSQLStatement {

    private static final long serialVersionUID = 1L;
    private PGWithClause      with;
    private boolean           only             = false;
    private List<SQLName>     using            = new ArrayList<SQLName>(2);
    private boolean           returning;
    private String            alias;

    public boolean isReturning() {
        return returning;
    }

    public void setReturning(boolean returning) {
        this.returning = returning;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<SQLName> getUsing() {
        return using;
    }

    public void setUsing(List<SQLName> using) {
        this.using = using;
    }

    public boolean isOnly() {
        return only;
    }

    public void setOnly(boolean only) {
        this.only = only;
    }

    public PGWithClause getWith() {
        return with;
    }

    public void setWith(PGWithClause with) {
        this.with = with;
    }

    protected void accept0(SQLASTVisitor visitor) {
        accept0((PGASTVisitor) visitor);
    }

    @Override
    public void accept0(PGASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, with);
            acceptChild(visitor, tableSource);
            acceptChild(visitor, using);
            acceptChild(visitor, where);
        }

        visitor.endVisit(this);
    }

}
