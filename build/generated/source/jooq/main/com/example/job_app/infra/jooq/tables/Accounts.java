/*
 * This file is generated by jOOQ.
 */
package com.example.job_app.infra.jooq.tables;


import com.example.job_app.infra.jooq.Cqq2l0ixquavsq6l;
import com.example.job_app.infra.jooq.Keys;
import com.example.job_app.infra.jooq.tables.records.AccountsRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Accounts extends TableImpl<AccountsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>cqq2l0ixquavsq6l.accounts</code>
     */
    public static final Accounts ACCOUNTS = new Accounts();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountsRecord> getRecordType() {
        return AccountsRecord.class;
    }

    /**
     * The column <code>cqq2l0ixquavsq6l.accounts.account_id</code>.
     */
    public final TableField<AccountsRecord, String> ACCOUNT_ID = createField(DSL.name("account_id"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.accounts.registered_datetime</code>.
     */
    public final TableField<AccountsRecord, LocalDateTime> REGISTERED_DATETIME = createField(DSL.name("registered_datetime"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.accounts.email</code>.
     */
    public final TableField<AccountsRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.accounts.password</code>.
     */
    public final TableField<AccountsRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.accounts.name</code>.
     */
    public final TableField<AccountsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.accounts.role</code>.
     */
    public final TableField<AccountsRecord, String> ROLE = createField(DSL.name("role"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    private Accounts(Name alias, Table<AccountsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Accounts(Name alias, Table<AccountsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>cqq2l0ixquavsq6l.accounts</code> table reference
     */
    public Accounts(String alias) {
        this(DSL.name(alias), ACCOUNTS);
    }

    /**
     * Create an aliased <code>cqq2l0ixquavsq6l.accounts</code> table reference
     */
    public Accounts(Name alias) {
        this(alias, ACCOUNTS);
    }

    /**
     * Create a <code>cqq2l0ixquavsq6l.accounts</code> table reference
     */
    public Accounts() {
        this(DSL.name("accounts"), null);
    }

    public <O extends Record> Accounts(Table<O> child, ForeignKey<O, AccountsRecord> key) {
        super(child, key, ACCOUNTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Cqq2l0ixquavsq6l.CQQ2L0IXQUAVSQ6L;
    }

    @Override
    public UniqueKey<AccountsRecord> getPrimaryKey() {
        return Keys.KEY_ACCOUNTS_PRIMARY;
    }

    @Override
    public List<UniqueKey<AccountsRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_ACCOUNTS_EMAIL);
    }

    @Override
    public Accounts as(String alias) {
        return new Accounts(DSL.name(alias), this);
    }

    @Override
    public Accounts as(Name alias) {
        return new Accounts(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Accounts rename(String name) {
        return new Accounts(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Accounts rename(Name name) {
        return new Accounts(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, LocalDateTime, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
