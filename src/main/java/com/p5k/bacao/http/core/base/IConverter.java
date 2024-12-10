/*
 * Copyright (c) 2023 Smartee Vina. All rights reserved.
 *
 */

package com.p5k.bacao.http.core.base;

import java.util.List;
public interface IConverter<D, E> {

    D toDto(E e);

    E toEntity(D d);

    List<D> toDTOs(List<E> e);

    List<E> toEntities(List<D> d);

}
