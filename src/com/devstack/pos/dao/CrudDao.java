package com.devstack.pos.dao;

import java.util.List;

// CrudDao va SuperDao vaala extends pannnina vendiya (UserDaoImpl, UserRoleDaoImpl, etc...) impl a SuperDao reference ikku poda ealum
public interface CrudDao<T,ID> extends SuperDao { // CrudDao la general method thaan eekum
    // izu 2du object type a keakkum T -> User type , ID -> User da id -> Integer type
    // CrudDao ikku record pass senja dynamically vendiya operations CrudDao ikku save seyya mudiyum
    // customer onda save pannakolem iza interface la eera functions a use seyyalaam (illatti ovvoru entity ikkum
    // ovvoru method theawa padum saveCustomer, saveItem, saveOrders, etc...  so izu la ellathukkum oru method thaan irukum)

    public boolean create(T t); // vendiya type a izukku anuppa ealum (Customer, User, Items, Orders)
    // boolean a return seyyum create aahina? true illatti false ena

    public T find(ID id); // data va search panna id a eduththuttu pohum but Type ennen indu theriya
    // but azu return seyrathu ennamalum oru object Type a aahum (may be oru Customer, User, etc,.. return seyyum)

    public boolean remove(ID id); // remove seyya eduththuttu porathum id ya aahum but return seyyum boolean
    // remove aana true illatti false

    public boolean modify(T t);// modify (update) aaha true illatti false a return seyyum
    // modify seyrathukku eduththuttu pora object ondaahum (type ondu) (may be a User, Customer)

    public List<T> loadAll(); // izu return seyrathu List onda yaahum but azuda type theriya
    // and idukku ondum pass seirulla
}
