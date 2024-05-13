import React from 'react';
import { Link } from 'gatsby';

import reactBuilding from '../assets/images/react-building.svg';

import './home.scss';
import PrimaryContent from '../components/primary-content/primary-content';

const HomePage: React.FC = () => {
  return (
    <section className="home">
      <div className="banner">
        <div className="banner__copy">
          <PrimaryContent>Main Content</PrimaryContent>
          <h1>Beam is Here!</h1>
          <p>
            This release candidate includes the latest version of the{' '}
            <a
              href="https://beam.kiteds.com/web/general/overview/design.html#overview"
              target="_blank"
              rel="noreferrer"
            >
              Beam Design Spec
            </a>{' '}
            along with numerous{' '}
            <Link to="/docs/changelog">development updates</Link>.
          </p>

          <p>
            We&#39;ve restructured the library to enable faster updates and also
            paid close attention to our upgrade path to facilitate quicker
            adoption for consumers.
          </p>
        </div>
        <div className="banner__image">
          <img src={reactBuilding} className="react-building" role="img" />
        </div>
      </div>

      <div className="home-content">
        <h3>Here&#39;s a few features we&#39;re excited about:</h3>
        <ul>
          <li>New and improved accessibility spec</li>
          <li>Updated visual spec</li>
          <li>Tokens</li>          
        </ul>

        <h2>Coming Soon</h2>
        <p>
          Future releases will include additional documentation including more
          detailed migration guides, but please reach out if you have questions
          specific to your project&#39;s consumption.
        </p>

        <p>
          We're continually hardening our
          components as we receive feedback from the community, evolving our
          support model, and significantly improving our quality strategy.  Your feedback is very important to us.
        </p>

        <p>Thanks!</p>

        <p>
          <strong>~ The Kite Web Team</strong>
        </p>
      </div>
    </section>
  );
};

export default HomePage;
