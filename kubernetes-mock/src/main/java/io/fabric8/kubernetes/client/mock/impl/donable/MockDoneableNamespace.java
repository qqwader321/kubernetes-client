/**
 * Copyright (C) 2015 Red Hat, Inc.                                        
 *                                                                         
 * Licensed under the Apache License, Version 2.0 (the "License");         
 * you may not use this file except in compliance with the License.        
 * You may obtain a copy of the License at                                 
 *                                                                         
 *         http://www.apache.org/licenses/LICENSE-2.0                      
 *                                                                         
 * Unless required by applicable law or agreed to in writing, software     
 * distributed under the License is distributed on an "AS IS" BASIS,       
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and     
 * limitations under the License.
 */

package io.fabric8.kubernetes.client.mock.impl.donable;

import io.fabric8.kubernetes.api.builder.Visitor;
import io.fabric8.kubernetes.api.model.Doneable;
import io.fabric8.kubernetes.api.model.DoneableNamespace;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceFluent;
import io.fabric8.kubernetes.api.model.NamespaceFluentImpl;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceFluent;
import io.fabric8.kubernetes.client.mock.MockDoneable;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;

public class MockDoneableNamespace extends NamespaceFluentImpl<MockDoneableNamespace> implements MockDoneable<Namespace> {

  private interface DelegateInterface extends Doneable<Namespace>, NamespaceFluent<DoneableNamespace> {}
  private final Visitor<Namespace> visitor = new Visitor<Namespace>() {
    @Override
    public void visit(Namespace pod) {
    }
  };

  private final DoneableNamespace delegate;

  public MockDoneableNamespace() {
    this.delegate = EasyMock.createMock(DoneableNamespace .class);
  }

  @Override
  public IExpectationSetters<Namespace> done() {
    return EasyMock.expect(delegate.done());
  }

  @Override
  public Void replay() {
    EasyMock.replay(delegate);
    return null;
  }

  @Override
  public void verify() {
    EasyMock.verify(delegate);
  }

  @Override
  public Doneable<Namespace> getDelegate() {
    return new DoneableNamespace(visitor) {
      @Override
      public Namespace done() {
        return delegate.done();
      }
    };
  }
}